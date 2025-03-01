package ru.noxly.fuelseller.models.models.dto;

import lombok.*;
import ru.noxly.fuelseller.models.enums.LotStatus;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class LotDto {

    private final Long id;

    private final String date;

    private final OilDepotDto oilDepot;

    private final FuelDto fuel;

    private final Double volumeOfFuel;

    private final Double remainingFuel;

    private final LotStatus status;

    private final Double totalPrice;

    private final Double pricePerTon;
}
