package ru.noxly.fuelseller.models.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "clients")
public class Client {
    @Id
    @Column
    private final UUID id;

    @Column
    private final String mail;

    @Column
    private final String name;

    @Column
    private final String middleName;

    @Column
    private final String surname;

    @Column
    private final String fullName;

    @Basic
    @Column
    private final OffsetDateTime createAt;
}
