package ru.noxly.fuelseller.models.models.dto;

import lombok.*;
import ru.noxly.fuelseller.models.enums.FuelType;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class FuelDto {

    private final Long kssFuel; //айди топлива

    private final FuelType fuelType;
}
