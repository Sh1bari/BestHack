package ru.noxly.efs.models.models.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class ImportLots {

    private final List<LotDto> newLots;

    private final List<OilDepotDto> newDepots;

    private final List<FuelDto> newFuels;
}
