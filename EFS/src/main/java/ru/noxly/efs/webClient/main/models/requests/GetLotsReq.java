package ru.noxly.efs.webClient.main.models.requests;

import lombok.*;
import ru.noxly.efs.common.PaginationRequest;
import ru.noxly.efs.models.enums.LotStatus;

import java.util.Set;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class GetLotsReq {

    private final Long kssFuel;

    private final Set<LotStatus> lotStatus;

    private final String oilDepotName;

    private final String oilDepotRegion;

    private final Double pricePerTonLower;

    private final Double pricePerTonMore;

    private final PaginationRequest pageable;
}
