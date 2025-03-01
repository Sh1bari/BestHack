package ru.noxly.authorization.validations;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.noxly.authorization.models.models.requests.LoginDtoReq;
import ru.noxly.authorization.repositories.RepoResolver;
import ru.noxly.validation.validation.ValidationHelper;

@Component
@RequiredArgsConstructor
public class LoginValidation {

    private final RepoResolver resolver;

    public void validate(Object obj) {
        if (obj instanceof LoginDtoReq) {
            validate((LoginDtoReq) obj);
        }
    }

    private void validate(LoginDtoReq req) {
        val user = resolver.getUserRepo().findByMail(req.getMail());

        ValidationHelper.init(req)
                .criticalStep(
                        o -> user.isPresent(),
                        "Bad credentials")
                .criticalStep(
                        o -> o.getPassword().equals(user.get().getPassword()),
                        "Bad credentials"
                )
                .validate();
    }

}
