package ru.noxly.efs.webClient.auth.models;

import lombok.*;
import ru.noxly.efs.models.enums.InviteStatus;
import ru.noxly.efs.models.enums.Role;
import ru.noxly.efs.models.enums.UserStatus;

import java.util.Set;
import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class UserDto {

    private final UUID id;

    private final String mail;

    private final String username;

    private final UserInfoDto userInfo;

    private final Set<Role> roles;

    private final UserStatus userStatus;

    private final InviteStatus inviteStatus;

    private final String createTime;
}
