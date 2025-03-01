package ru.noxly.authorization.models.models.requests;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class RegisterUserDtoReq {

    private final String name;

    private final String middleName;

    private final String surname;

    private final String username;

    private final String mail;

    private final String password;
}
