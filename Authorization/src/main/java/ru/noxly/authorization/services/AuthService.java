package ru.noxly.authorization.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noxly.authorization.models.entities.User;
import ru.noxly.authorization.models.entities.UserInfo;
import ru.noxly.authorization.models.models.requests.RegisterUserDtoReq;
import ru.noxly.authorization.repositories.RepoResolver;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RepoResolver resolver;
    private final ConversionService conversionService;

    @Transactional
    public User registerUser(final RegisterUserDtoReq request) {
        val user = conversionService.convert(request, User.class);
        val userEntity = resolver.resolve(User.class).save(user);

        val userInfoTemp = conversionService.convert(request, UserInfo.class);
        val userInfo = Objects.requireNonNull(userInfoTemp).toBuilder()
                .setUser(userEntity)
                .build();
        val entityUserInfo = resolver.resolve(UserInfo.class).save(userInfo);

        return userEntity.toBuilder()
                .setUserInfo(entityUserInfo)
                .build();
    }
}
