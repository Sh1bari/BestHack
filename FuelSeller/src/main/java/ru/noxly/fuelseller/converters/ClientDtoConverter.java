package ru.noxly.fuelseller.converters;

import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import ru.noxly.fuelseller.models.entites.Client;
import ru.noxly.fuelseller.models.entites.Order;
import ru.noxly.fuelseller.models.models.dto.ClientDto;
import ru.noxly.fuelseller.models.models.dto.OrderDto;
import ru.noxly.fuelseller.utils.Formatter;

@Component
@RequiredArgsConstructor
public class ClientDtoConverter implements Converter<Client, ClientDto> {

    private final ConversionService conversionService;

    @PostConstruct
    public void register() {
        if (conversionService instanceof DefaultConversionService) {
            ((DefaultConversionService) conversionService).addConverter(this);
        }
    }

    @Override
    public ClientDto convert(@NonNull final Client source) {
        return ClientDto.init()
                .setId(source.getId())
                .setMail(source.getMail())
                .setName(source.getName())
                .setMiddleName(source.getMiddleName())
                .setSurname(source.getSurname())
                .setFullName(source.getFullName())
                .setCreateAt(source.getCreateAt().format(Formatter.formatter))
                .build();
    }
}
