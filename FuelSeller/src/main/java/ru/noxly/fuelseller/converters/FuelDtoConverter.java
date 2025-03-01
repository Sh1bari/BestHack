package ru.noxly.fuelseller.converters;

import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import ru.noxly.fuelseller.models.entites.Fuel;
import ru.noxly.fuelseller.models.models.dto.FuelDto;

@Component
@RequiredArgsConstructor
public class FuelDtoConverter implements Converter<Fuel, FuelDto> {

    private final ConversionService conversionService;

    @PostConstruct
    public void register() {
        if (conversionService instanceof DefaultConversionService) {
            ((DefaultConversionService) conversionService).addConverter(this);
        }
    }
    @Override
    public FuelDto convert(@NonNull final Fuel source) {
        val fuel = FuelDto.init()
                .setFuelType(source.getFuelType())
                .setKssFuel(source.getId())
                .build();
        return fuel;
    }
}
