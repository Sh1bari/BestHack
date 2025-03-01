package ru.noxly.authorization.configs.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.noxly.authorization.models.models.requests.LoginDtoReq;
import ru.noxly.authorization.models.models.requests.RegisterUserDtoReq;
import ru.noxly.authorization.validations.LoginValidation;
import ru.noxly.authorization.validations.RegisterUserValidation;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class Validation {

    private final RegisterUserValidation registerUserValidation;
    private final LoginValidation loginValidation;

    public void init(Map<List<Class<?>>, Consumer<Object>> validators) {
        validators.put(
                List.of(RegisterUserDtoReq.class),
                registerUserValidation::validate
        );
        validators.put(
                List.of(LoginDtoReq.class),
                loginValidation::validate
        );
    }


}
