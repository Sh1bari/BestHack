package ru.noxly.efs.models.models.dto;

import lombok.*;
import ru.noxly.efs.models.enums.FuelType;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class LotDto {

    private final Long id;

    private final FuelType fuelType;

    private final String oilDepotName;

    private final String oilDepotRegion;

    private final Double remainingFuel;

    private final Double pricePerTon;
}
