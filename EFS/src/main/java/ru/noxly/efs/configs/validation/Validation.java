package ru.noxly.efs.configs.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class Validation {


    public void init(Map<List<Class<?>>, Consumer<Object>> validators) {
        /*validators.put(
                List.of(RegisterUserDtoReq.class),
                registerUserValidation::validate
        );*/
    }


}
