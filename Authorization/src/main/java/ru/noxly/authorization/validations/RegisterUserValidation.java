package ru.noxly.authorization.validations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.noxly.authorization.models.models.requests.RegisterUserDtoReq;
import ru.noxly.authorization.repositories.RepoResolver;
import ru.noxly.validation.validation.ValidationHelper;

@Component
@RequiredArgsConstructor
public class RegisterUserValidation {

    private final RepoResolver resolver;

    public void validate(Object obj) {
        if (obj instanceof RegisterUserDtoReq) {
            validate((RegisterUserDtoReq) obj);
        }
    }

    private void validate(RegisterUserDtoReq req) {
        ValidationHelper.init(req)
                .step(
                        o -> !resolver.getUserRepo().existsByMail(o.getMail()),
                        "mail",
                        "mail already exists"
                )
                .step(
                        o -> !resolver.getUserRepo().existsByUsername(o.getUsername()),
                        "username",
                        "username already exists"
                )
                .validate();
    }
}
