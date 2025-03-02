package ru.noxly.fuelseller.models.models.requests;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class CreateClientDto {

    @Id
    private final UUID id;

    @Column
    private final String mail;

    @Column
    private final String name;

    @Column
    private final String middleName;

    @Column
    private final String surname;
}
