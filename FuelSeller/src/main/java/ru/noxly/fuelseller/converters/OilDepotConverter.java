package ru.noxly.fuelseller.converters;

import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import ru.noxly.fuelseller.models.entites.OilDepot;
import ru.noxly.fuelseller.models.models.dto.OilDepotDto;

@Component
@RequiredArgsConstructor
public class OilDepotConverter implements Converter<OilDepot, OilDepotDto> {

    private final ConversionService conversionService;

    @PostConstruct
    public void register() {
        if (conversionService instanceof DefaultConversionService) {
            ((DefaultConversionService) conversionService).addConverter(this);
        }
    }
    @Override
    public OilDepotDto convert(@NonNull final OilDepot source) {
        return OilDepotDto.init()
                .setKsssnb(source.getId())
                .setName(source.getName())
                .setRegion(source.getRegion())
                .build();
    }
}
