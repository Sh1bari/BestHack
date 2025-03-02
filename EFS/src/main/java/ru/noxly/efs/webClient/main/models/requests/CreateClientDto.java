package ru.noxly.efs.webClient.main.models.requests;

import lombok.*;

import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class CreateClientDto {

    private final UUID id;

    private final String mail;

    private final String name;

    private final String middleName;

    private final String surname;
}
