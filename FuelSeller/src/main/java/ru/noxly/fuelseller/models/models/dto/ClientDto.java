package ru.noxly.fuelseller.models.models.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class ClientDto {

    private final UUID id;

    private final String mail;

    private final String name;

    private final String middleName;

    private final String surname;

    private final String fullName;

    private final String createAt;
}
