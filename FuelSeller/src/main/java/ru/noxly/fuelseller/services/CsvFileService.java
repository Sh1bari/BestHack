package ru.noxly.fuelseller.services;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noxly.fuelseller.models.entites.Fuel;
import ru.noxly.fuelseller.models.entites.Lot;
import ru.noxly.fuelseller.models.entites.OilDepot;
import ru.noxly.fuelseller.models.models.dto.LotFromCsvDto;
import ru.noxly.fuelseller.repositories.RepoResolver;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvFileService {

    private final RepoResolver resolver;

    private final ConversionService conversionService;

    @Transactional
    public void process(List<LotFromCsvDto> lots){
        val entities = lots.stream()
                .map(o -> conversionService.convert(o, Lot.class))
                .toList();

        for(val lot : entities){
            val fuel = resolver.resolve(Fuel.class).findById(lot.getFuel().getId());
            val oilDepot = resolver.resolve(OilDepot.class).findById(lot.getOilDepot().getId());
            val entity = lot.toBuilder()
                    .setFuel(fuel)
                    .setOilDepot(oilDepot)
                    .build();

            resolver.resolve(Lot.class).save(entity);
        }
    }
}
