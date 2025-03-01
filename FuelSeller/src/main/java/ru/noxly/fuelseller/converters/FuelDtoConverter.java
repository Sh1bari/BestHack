package ru.noxly.fuelseller.converters;

import lombok.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.fuelseller.models.entites.Fuel;
import ru.noxly.fuelseller.models.models.dto.FuelDto;

@Component
public class FuelDtoConverter implements Converter<Fuel, FuelDto> {
    @Override
    public FuelDto convert(@NonNull final Fuel source) {
        val fuel = FuelDto.init()
                .setFuelType(source.getFuelType())
                .setKssFuel(source.getId())
                .build();
        return fuel;
    }
}
