package ru.noxly.efs.webClient.main.models.requests;

import lombok.*;
import ru.noxly.efs.common.PaginationRequest;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class GetOilDepotsReq {

    private final String name;

    private final String region;

    private final PaginationRequest pageable;
}
