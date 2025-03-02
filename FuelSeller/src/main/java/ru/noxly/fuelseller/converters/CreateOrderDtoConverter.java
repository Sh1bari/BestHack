package ru.noxly.fuelseller.converters;

import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import ru.noxly.fuelseller.models.entites.Client;
import ru.noxly.fuelseller.models.entites.Order;
import ru.noxly.fuelseller.models.models.requests.CreateClientDto;
import ru.noxly.fuelseller.models.models.requests.CreateOrderDto;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class CreateOrderDtoConverter implements Converter<CreateOrderDto, Order> {

    private final ConversionService conversionService;

    @PostConstruct
    public void register() {
        if (conversionService instanceof DefaultConversionService) {
            ((DefaultConversionService) conversionService).addConverter(this);
        }
    }

    @Override
    public Order convert(@NonNull final CreateOrderDto source) {
        return Order.init()
                .setDate(OffsetDateTime.now())
                .setDate(OffsetDateTime.now())
                .setVolume(source.getVolume())
                .setDeliveryType(source.getDeliveryType())
                .build();
    }
}
