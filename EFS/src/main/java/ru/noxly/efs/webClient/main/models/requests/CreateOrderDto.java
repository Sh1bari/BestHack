package ru.noxly.efs.webClient.main.models.requests;

import lombok.*;
import ru.noxly.efs.models.enums.DeliveryType;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class CreateOrderDto {

    private final Long lotId;

    private final Long ksssnb;

    private final Long kssFuel;

    private final Double volume;

    private final DeliveryType deliveryType;

    private final CreateClientDto client;
}
