package ru.noxly.authorization.converters;

import lombok.NonNull;
import lombok.val;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.authorization.models.entities.User;
import ru.noxly.authorization.models.enums.InviteStatus;
import ru.noxly.authorization.models.models.requests.RegisterUserDtoReq;

import java.time.OffsetDateTime;
import java.util.Set;

import static ru.noxly.authorization.models.enums.Role.ROLE_USER;
import static ru.noxly.authorization.models.enums.UserStatus.ACTIVE;

@Component
public class RegisterUserDtoReqToUserConverter implements Converter<RegisterUserDtoReq, User> {

    @Override
    public User convert(@NonNull final RegisterUserDtoReq source) {
        val user = User.init()
                .setMail(source.getMail())
                .setUsername(source.getUsername())
                .setPassword(source.getPassword())
                .setRoles(Set.of(ROLE_USER))
                .setInviteStatus(InviteStatus.ACTIVE)
                .setUserStatus(ACTIVE)
                .setCreateTime(OffsetDateTime.now())
                .build();

        return user;
    }
}
