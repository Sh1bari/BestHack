package ru.noxly.fuelseller.converters;

import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import ru.noxly.fuelseller.models.entites.Client;
import ru.noxly.fuelseller.models.entites.Order;
import ru.noxly.fuelseller.models.models.dto.*;
import ru.noxly.fuelseller.models.models.requests.CreateClientDto;
import ru.noxly.fuelseller.utils.Formatter;

@Component
@RequiredArgsConstructor
public class OrderDtoConverter implements Converter<Order, OrderDto> {

    private final ConversionService conversionService;

    @PostConstruct
    public void register() {
        if (conversionService instanceof DefaultConversionService) {
            ((DefaultConversionService) conversionService).addConverter(this);
        }
    }

    @Override
    public OrderDto convert(@NonNull final Order source) {
        return OrderDto.init()
                .setId(source.getId())
                .setDate(source.getDate().format(Formatter.formatter))
                .setLot(conversionService.convert(source.getLot(), LotDto.class))
                .setOilDepot(conversionService.convert(source.getOilDepot(), OilDepotDto.class))
                .setFuel(conversionService.convert(source.getFuel(), FuelDto.class))
                .setVolume(source.getVolume())
                .setDeliveryType(source.getDeliveryType())
                .setClient(conversionService.convert(source.getClient(), ClientDto.class))
                .build();
    }
}
