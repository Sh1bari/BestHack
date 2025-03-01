package ru.noxly.fuelseller.converters;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.fuelseller.models.entites.OilDepot;
import ru.noxly.fuelseller.models.models.dto.OilDepotDto;

@Component
public class OilDepotConverter implements Converter<OilDepot, OilDepotDto> {
    @Override
    public OilDepotDto convert(@NonNull final OilDepot source) {
        return OilDepotDto.init()
                .setKsssnb(source.getId())
                .setName(source.getName())
                .setRegion(source.getRegion())
                .build();
    }
}
