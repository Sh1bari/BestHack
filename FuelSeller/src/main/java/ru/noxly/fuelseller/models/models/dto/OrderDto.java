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

    private final Long lotId;

    private final Long ksssnb; //айди нефтебазы

    private final Long kssFuel; //айди топлива

    private final Double volume; // количество купленного топлива

    private final DeliveryType deliveryType;

    private final UUID clientId;
}
