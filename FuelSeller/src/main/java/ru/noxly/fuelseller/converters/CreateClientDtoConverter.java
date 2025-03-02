package ru.noxly.fuelseller.converters;

import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import ru.noxly.fuelseller.models.entites.Client;
import ru.noxly.fuelseller.models.models.requests.CreateClientDto;

import javax.swing.*;
import java.time.OffsetDateTime;

import static java.lang.String.format;
@Component
@RequiredArgsConstructor
public class CreateClientDtoConverter implements Converter<CreateClientDto, Client> {

    private final ConversionService conversionService;

    @PostConstruct
    public void register() {
        if (conversionService instanceof DefaultConversionService) {
            ((DefaultConversionService) conversionService).addConverter(this);
        }
    }

    @Override
    public Client convert(@NonNull final CreateClientDto source) {
        return Client.init()
                .setId(source.getId())
                .setCreateAt(OffsetDateTime.now())
                .setMail(source.getMail())
                .setName(source.getName())
                .setMiddleName(source.getMiddleName())
                .setSurname(source.getSurname())
                .setFullName(
                        format("%s %s %s",
                                source.getSurname(),
                                source.getName(),
                                source.getMiddleName())
                )
                .build();
    }
}
