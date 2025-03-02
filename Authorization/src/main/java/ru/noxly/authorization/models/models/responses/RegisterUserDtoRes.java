package ru.noxly.authorization.models.models.responses;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class RegisterUserDtoRes {
    private final String test;
}
