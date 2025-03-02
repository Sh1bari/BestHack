package ru.noxly.fuelseller.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noxly.fuelseller.models.entites.CsvFile;
import ru.noxly.fuelseller.models.enums.CsvFileStatus;
import ru.noxly.fuelseller.models.enums.LotStatus;
import ru.noxly.fuelseller.models.models.dto.LotFromCsvDto;
import ru.noxly.fuelseller.models.models.dto.ReadLotsFromCsvDto;
import ru.noxly.fuelseller.repositories.RepoResolver;
import ru.noxly.fuelseller.specifications.CsvFileSpecifications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.noxly.fuelseller.utils.Formatter.parseDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class FtpService {

    private final DefaultFtpSessionFactory ftpSessionFactory;

    private final CsvFileService csvFileService;

    private final RepoResolver resolver;

    @Transactional
    public void processScvFiles() {
        val list = listOfNewCsvFiles();
        for (val fileName : list) {
            log.info("Обработка CSV файла: {}", fileName);
            val lots = readLotsFromCsv(fileName);

            csvFileService.process(lots.getLots());
            log.info("Добавление {} лотов", lots.getLots().size());

            val csvFile = CsvFile.init()
                    .setName(lots.getFilename())
                    .setStatus(CsvFileStatus.PROCESSED)
                    .build();

            log.info("Успешная обработка CSV файла: {}", fileName);
            resolver.resolve(CsvFile.class).save(csvFile);
        }
    }

    /**
     * Получение списка CSV-файлов в директории FTP.
     */
    private List<String> listCsvFiles() {
        try (var session = ftpSessionFactory.getSession()) {
            FTPFile[] files = session.list(".");

            return Arrays.stream(files)
                    .map(FTPFile::getName)
                    .filter(file -> file.endsWith(".csv"))
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при получении списка файлов", e);
        }
    }


    private List<String> listOfNewCsvFiles() {
        val processedFiles = resolver.resolve(CsvFile.class)
                .findAll(Specification.where(CsvFileSpecifications.hasStatus(CsvFileStatus.PROCESSED)))
                .stream()
                .map(CsvFile::getName)
                .collect(Collectors.toSet());

        val files = listCsvFiles();

        return files.stream()
                .filter(file -> !processedFiles.contains(file))
                .toList();
    }

    /**
     * Читает CSV-файл и парсит его в список LotFromCsvDto.
     */
    private ReadLotsFromCsvDto readLotsFromCsv(String fileName) {
        try (var session = ftpSessionFactory.getSession()) {

            try (
                    InputStream inputStream = session.readRaw(fileName);
                    InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(isr)
            ) {

                List<String> lines = reader.lines().toList();

                val lots = lines.stream()
                        .skip(1) // Пропускаем заголовок
                        .map(this::parseLotFromCsv)
                        .filter(Objects::nonNull)
                        .toList();
                return ReadLotsFromCsvDto.init()
                        .setLots(lots)
                        .setFilename(fileName)
                        .build();
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении CSV-файла: " + fileName, e);
        }
    }

    /**
     * Разбирает строку CSV в объект LotFromCsvDto.
     */
    private LotFromCsvDto parseLotFromCsv(String line) {
        try {
            val parts = line.split(";");

            if (parts.length < 7) return null; // Если не хватает столбцов — пропускаем строку

            Long id = parseLong(parts[0]);
            OffsetDateTime date = parseDate(parts[1]);
            Long oilDepot = parseLong(parts[2]);
            Long fuel = parseLong(parts[3]);
            Double volumeOfFuel = parseDouble(parts[4]);
            Double remainingFuel = parseDouble(parts[5]);
            LotStatus status = LotStatus.ACCEPTED;
            Double totalPrice = parseDouble(parts[6]);
            Double pricePerTon = parseDouble(parts[7]);

            // Если какие-то ключевые поля пустые — пропускаем строку
            if (id == null || date == null || oilDepot == null || fuel == null ||
                    volumeOfFuel == null || remainingFuel == null ||
                    totalPrice == null || pricePerTon == null) {
                return null;
            }

            return LotFromCsvDto.init()
                    .setId(id)
                    .setDate(date)
                    .setOilDepot(oilDepot)
                    .setFuel(fuel)
                    .setVolumeOfFuel(volumeOfFuel)
                    .setRemainingFuel(remainingFuel)
                    .setStatus(status)
                    .setTotalPrice(totalPrice)
                    .setPricePerTon(pricePerTon)
                    .build();

        } catch (Exception e) {
            System.err.println("Ошибка парсинга строки: " + line);
            return null; // Пропускаем строку при ошибке
        }
    }

    // Методы для безопасного парсинга чисел
    private Long parseLong(String value) {
        try {
            return value.isBlank() ? null : Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double parseDouble(String value) {
        try {
            if (value == null || value.isBlank()) {
                return null;
            }
            return Double.parseDouble(value.trim().replace(",", ".")); // Заменяем запятую на точку
        } catch (NumberFormatException e) {
            System.err.println("Ошибка парсинга: " + value);
            return null;
        }
    }
}
