package ru.noxly.efs.models.models.dto;

import lombok.*;
import ru.noxly.efs.models.enums.DeliveryType;

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

    private final Long ksssnb;

    private final Long ksssFuel;

    private final Double volume;

    private final DeliveryType deliveryType;

    private final UUID clientId;
}
