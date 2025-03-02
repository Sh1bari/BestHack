package ru.noxly.authorization.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noxly.authorization.exceptions.GeneralException;
import ru.noxly.authorization.models.entities.User;
import ru.noxly.authorization.models.entities.UserInfo;
import ru.noxly.authorization.models.models.requests.LoginDtoReq;
import ru.noxly.authorization.models.models.requests.RefreshDto;
import ru.noxly.authorization.models.models.requests.RegisterUserDtoReq;
import ru.noxly.authorization.repositories.RepoResolver;

import java.util.Objects;
import java.util.UUID;

import static ru.noxly.authorization.utils.JwtUtil.getClaims;

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

    public User login(final LoginDtoReq request) {
        val user = resolver.getUserRepo().findByMail(request.getMail()).get();
        return user;
    }

    public User refreshToken(final RefreshDto request) {
        val claims = getClaims(request.getRefreshToken());
        if (!claims.getBody().get("tokenType").toString().equals("refresh")) {
            throw new GeneralException(409, "Wrong token type");
        }
        val user = resolver.resolve(User.class)
                .findById(UUID.fromString(claims.getBody().get("sub").toString()));

        return user;
    }
}
