package ru.noxly.authorization.converters;

import lombok.NonNull;
import lombok.val;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.authorization.models.entities.UserInfo;
import ru.noxly.authorization.models.models.requests.RegisterUserDtoReq;

import static java.lang.String.format;

@Component
public class RegisterUserDtoReqToUserInfoConverter implements Converter<RegisterUserDtoReq, UserInfo> {

    @Override
    public UserInfo convert(@NonNull final RegisterUserDtoReq source) {
        val userInfo = UserInfo.init()
                .setName(source.getName())
                .setMiddleName(source.getMiddleName())
                .setSurname(source.getSurname())
                .setFullName(format("%s %s %s", source.getSurname(), source.getName(), source.getMiddleName()))
                .build();

        return userInfo;
    }
}
