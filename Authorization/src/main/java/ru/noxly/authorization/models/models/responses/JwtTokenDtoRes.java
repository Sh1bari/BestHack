package ru.noxly.authorization.models.models.responses;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class JwtTokenDtoRes {
    private final String access;
    private final String refresh;
}
