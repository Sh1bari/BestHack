package ru.noxly.authorization.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import ru.noxly.authorization.models.enums.InviteStatus;
import ru.noxly.authorization.models.enums.Role;
import ru.noxly.authorization.models.enums.UserStatus;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "users")
public class User {
    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private final UUID id;

    @Column(unique = true)
    private final String mail;

    @Column(unique = true)
    private final String username;

    @Column
    private final String password;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private final UserInfo userInfo;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private final Set<Role> roles;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private final UserStatus userStatus;

    @Column(name = "invite_status")
    @Enumerated(EnumType.STRING)
    private final InviteStatus inviteStatus;

    @Basic
    private final OffsetDateTime createTime;
}
