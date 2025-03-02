package ru.noxly.fuelseller.configs.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.noxly.fuelseller.models.models.requests.CreateOrderDto;
import ru.noxly.fuelseller.validations.CreateOrderValidation;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class Validation {
    private final CreateOrderValidation createOrderValidation;

    public void init(Map<List<Class<?>>, Consumer<Object>> validators) {
        validators.put(
                List.of(CreateOrderDto.class),
                createOrderValidation::validate
        );
    }


}
