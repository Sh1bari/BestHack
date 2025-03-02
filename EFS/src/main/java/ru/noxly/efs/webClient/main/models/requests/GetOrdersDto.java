package ru.noxly.efs.webClient.main.models.requests;

import lombok.*;
import ru.noxly.efs.common.PaginationRequest;

import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class GetOrdersDto {
    private final PaginationRequest pageRequest;
    private final UUID userId;
}
