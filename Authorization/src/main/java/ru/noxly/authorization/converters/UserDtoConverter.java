package ru.noxly.authorization.converters;

import lombok.NonNull;
import lombok.val;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.authorization.models.entities.User;
import ru.noxly.authorization.models.models.dto.UserDto;
import ru.noxly.authorization.models.models.dto.UserInfoDto;
import ru.noxly.authorization.utils.Formatter;

import static ru.noxly.authorization.utils.CommonUtils.nullOrApply;

@Component
public class UserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(@NonNull final User source) {
        val user = UserDto.init()
                .setId(source.getId())
                .setMail(source.getMail())
                .setUsername(source.getUsername())
                .setUserInfo(
                        UserInfoDto.init()
                                .setName(source.getUserInfo().getName())
                                .setMiddleName(source.getUserInfo().getMiddleName())
                                .setSurname(source.getUserInfo().getSurname())
                                .setFullName(source.getUserInfo().getFullName())
                                .build()
                )
                .setRoles(source.getRoles())
                .setUserStatus(source.getUserStatus())
                .setInviteStatus(source.getInviteStatus())
                .setCreateTime(nullOrApply(source.getCreateTime(), Formatter.formatter::format))
                .build();

        return user;
    }
}
