package ru.noxly.authorization.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.noxly.authorization.exceptions.WrongTokenExc;
import ru.noxly.authorization.models.entities.User;
import ru.noxly.authorization.models.models.dto.UserDto;
import ru.noxly.authorization.repositories.RepoResolver;
import ru.noxly.authorization.utils.JwtUtil;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RepoResolver resolver;
    private final ConversionService conversionService;

    public UserDto validateUser(final String token) {
        val claims = JwtUtil.getClaims(token);
        if (!claims.getBody().get("tokenType").toString().equals("access")) {
            throw new WrongTokenExc();
        }
        val userId = UUID.fromString(claims.getBody().get("sub").toString());
        val user = resolver.resolve(User.class).findById(userId);

        return conversionService.convert(user, UserDto.class);
    }
}
