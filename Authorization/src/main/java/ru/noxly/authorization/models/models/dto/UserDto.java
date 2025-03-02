package ru.noxly.authorization.models.models.dto;

import lombok.*;
import ru.noxly.authorization.models.enums.InviteStatus;
import ru.noxly.authorization.models.enums.Role;
import ru.noxly.authorization.models.enums.UserStatus;

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
