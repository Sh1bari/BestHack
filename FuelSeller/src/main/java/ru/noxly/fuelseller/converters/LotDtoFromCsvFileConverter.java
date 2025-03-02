package ru.noxly.fuelseller.converters;

import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import ru.noxly.fuelseller.models.entites.Fuel;
import ru.noxly.fuelseller.models.entites.Lot;
import ru.noxly.fuelseller.models.entites.OilDepot;
import ru.noxly.fuelseller.models.models.dto.LotFromCsvDto;

@Component
@RequiredArgsConstructor
public class LotDtoFromCsvFileConverter implements Converter<LotFromCsvDto, Lot> {

    private final ConversionService conversionService;

    @PostConstruct
    public void register() {
        if (conversionService instanceof DefaultConversionService) {
            ((DefaultConversionService) conversionService).addConverter(this);
        }
    }

    @Override
    public Lot convert(@NonNull final LotFromCsvDto source) {
        return Lot.init()
                .setId(source.getId())
                .setFuel(
                        Fuel.init()
                                .setId(source.getFuel())
                                .build()
                )
                .setOilDepot(
                        OilDepot.init()
                                .setId(source.getOilDepot())
                                .build()
                )
                .setDate(source.getDate())
                .setVolumeOfFuel(source.getVolumeOfFuel())
                .setRemainingFuel(source.getRemainingFuel())
                .setStatus(source.getStatus())
                .setTotalPrice(source.getTotalPrice())
                .setPricePerTon(source.getPricePerTon())
                .build();
    }
}
