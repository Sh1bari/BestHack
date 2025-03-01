package ru.noxly.fuelseller.converters;

import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import ru.noxly.fuelseller.models.entites.Fuel;
import ru.noxly.fuelseller.models.entites.Lot;
import ru.noxly.fuelseller.models.models.dto.FuelDto;
import ru.noxly.fuelseller.models.models.dto.LotDto;
import ru.noxly.fuelseller.models.models.dto.OilDepotDto;
import ru.noxly.fuelseller.utils.Formatter;

import static ru.noxly.fuelseller.utils.CommonUtils.nullOrApply;

@Component
@RequiredArgsConstructor
public class LotDtoConverter implements Converter<Lot, LotDto> {

    private final ConversionService conversionService;

    @PostConstruct
    public void register() {
        if (conversionService instanceof DefaultConversionService) {
            ((DefaultConversionService) conversionService).addConverter(this);
        }
    }
    @Override
    public LotDto convert(@NonNull final Lot source) {
        return LotDto.init()
                .setDate(nullOrApply(source.getDate(), Formatter.formatter::format))
                .setId(source.getId())
                .setStatus(source.getStatus())
                .setTotalPrice(source.getTotalPrice())
                .setPricePerTon(source.getPricePerTon())
                .setRemainingFuel(source.getRemainingFuel())
                .setVolumeOfFuel(source.getVolumeOfFuel())
                .setFuel(nullOrApply(source.getFuel(), o-> conversionService.convert(o, FuelDto.class)))
                .setOilDepot(nullOrApply(source.getOilDepot(), o-> conversionService.convert(o, OilDepotDto.class)))
                .build();
    }
}
