package ru.noxly.fuelseller.models.models.requests;

import lombok.*;
import ru.noxly.fuelseller.common.PaginationRequest;

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
