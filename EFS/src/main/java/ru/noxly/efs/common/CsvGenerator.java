package ru.noxly.efs.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static ru.noxly.efs.utils.Formatter.formatter;

public class CsvGenerator {
    private static final Random random = new Random();

    public static File generateCsvFile(Integer from, Integer to) {
        String fileName = String.format("lots_data_%s_%s.csv", from, to);
        String filePath = Paths.get(System.getProperty("user.dir"), fileName).toString();
        File file = new File(filePath);

        try (FileWriter writer = new FileWriter(file)) {
            // Заголовок CSV
            writer.append("id;date;oilDepot;fuel;volumeOfFuel;remainingFuel;totalPrice;pricePerTon\n");

            for (int i = from; i <= to; i++) {
                String date = randomDate2026();
                long oilDepot = random.nextInt(20) + 1;  // ID нефтебазы (1-20)
                long fuel = random.nextInt(5) + 1;       // ID топлива (1-5)
                double pricePerTon = round(randomDouble(40000, 60000), 2);
                int volumeOfFuel = random.nextInt(90001) + 10000; // 10000 - 100000 литров
                double totalPrice = round(pricePerTon * volumeOfFuel, 2);

                // Запись в файл
                writer.append(String.format("%d;%s;%d;%d;%d;%d;%.2f;%.2f\n",
                        i, date, oilDepot, fuel, volumeOfFuel, volumeOfFuel, totalPrice, pricePerTon));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    // Функция генерации случайной даты в 2026 году
    public static String randomDate2026() {
        // Генерируем случайный день в 2026 году
        LocalDate start = LocalDate.of(2026, 1, 1);
        LocalDate end = LocalDate.of(2026, 12, 31);

        long randomEpochDay = ThreadLocalRandom.current().nextLong(start.toEpochDay(), end.toEpochDay());
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);

        // Генерируем случайное время суток
        int randomHour = ThreadLocalRandom.current().nextInt(0, 24);
        int randomMinute = ThreadLocalRandom.current().nextInt(0, 60);
        int randomSecond = ThreadLocalRandom.current().nextInt(0, 60);
        int randomMillis = ThreadLocalRandom.current().nextInt(0, 1000);

        // Создаем OffsetDateTime с таймзоной +03:00
        OffsetDateTime randomDateTime = OffsetDateTime.of(
                randomDate,
                LocalTime.of(randomHour, randomMinute, randomSecond, randomMillis * 1_000_000),
                ZoneOffset.of("+03:00") // Москва или другой нужный часовой пояс
        );

        // Форматируем дату по заданному паттерну
        return randomDateTime.format(formatter);
    }

    // Генерация случайного числа с плавающей точкой в заданном диапазоне
    private static double randomDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    // Округление чисел до 2 знаков после запятой
    private static double round(double value, int places) {
        return Math.round(value * Math.pow(10, places)) / Math.pow(10, places);
    }
}
