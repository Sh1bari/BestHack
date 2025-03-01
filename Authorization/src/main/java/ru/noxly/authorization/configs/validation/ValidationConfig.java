package ru.noxly.authorization.configs.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class ValidationConfig {

    @Bean
    public Map<List<Class<?>>, Consumer<Object>> validators(Validation validation) {
        Map<List<Class<?>>, Consumer<Object>> validators = new HashMap<>();
        validation.init(validators);
        return validators;
    }
}
