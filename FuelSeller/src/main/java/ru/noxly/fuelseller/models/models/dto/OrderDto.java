package ru.noxly.fuelseller.models.models.dto;

import lombok.*;
import ru.noxly.fuelseller.models.enums.DeliveryType;

import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class OrderDto {

    private final Long id;

    private final String date;

    private final LotDto lot;

    private final OilDepotDto oilDepot;

    private final FuelDto fuel;

    private final Double volume;

    private final DeliveryType deliveryType;

    private final ClientDto client;
}
