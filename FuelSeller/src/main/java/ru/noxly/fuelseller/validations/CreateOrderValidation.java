package ru.noxly.fuelseller.validations;

import lombok.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.noxly.fuelseller.models.entites.Client;
import ru.noxly.fuelseller.models.models.requests.CreateOrderDto;
import ru.noxly.fuelseller.repositories.RepoResolver;
import ru.noxly.validation.validation.ValidationHelper;

@Component
@RequiredArgsConstructor
public class CreateOrderValidation {

    private final RepoResolver resolver;

    private final ConversionService conversionService;

    public void validate(Object obj) {
        if (obj instanceof CreateOrderDto) {
            validate((CreateOrderDto) obj);
        }
    }

    private void validate(CreateOrderDto req) {
        ValidationHelper.init(req)
                .step(o -> {
                            if (!resolver.getClientRepository().existsById(req.getClient().getId())) {
                                val client = conversionService.convert(req.getClient(), Client.class);
                                resolver.resolve(Client.class).save(client);
                            }
                        }
                )
                .validate();
    }

}
