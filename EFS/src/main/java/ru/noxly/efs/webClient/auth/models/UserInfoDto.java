package ru.noxly.efs.webClient.auth.models;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class UserInfoDto {

    private final String name;

    private final String middleName;

    private final String surname;

    private final String fullName;
}
