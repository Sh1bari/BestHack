package ru.noxly.authorization.models.models.requests;

import lombok.*;
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class RefreshDto {
    private final String refreshToken;
}
