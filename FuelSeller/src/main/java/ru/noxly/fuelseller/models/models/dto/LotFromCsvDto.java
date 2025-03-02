package ru.noxly.fuelseller.models.models.dto;

import lombok.*;
import ru.noxly.fuelseller.models.enums.LotStatus;

import java.time.OffsetDateTime;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class LotFromCsvDto {

    private final Long id;

    private final OffsetDateTime date;

    private final Long oilDepot;

    private final Long fuel;

    private final Double volumeOfFuel;

    private final Double remainingFuel;

    private final LotStatus status;

    private final Double totalPrice;

    private final Double pricePerTon;
}
