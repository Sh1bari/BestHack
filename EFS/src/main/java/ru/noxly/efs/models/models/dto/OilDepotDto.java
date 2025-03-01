package ru.noxly.efs.models.models.dto;

import lombok.*;
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class OilDepotDto {

    private final Long ksssnb; //айди нефтебазы

    private final String name;

    private final String region;
}
