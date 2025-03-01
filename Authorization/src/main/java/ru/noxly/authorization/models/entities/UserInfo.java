package ru.noxly.authorization.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private final User user;

    private final String name;

    private final String middleName;

    private final String surname;

    private final String fullName;

}
