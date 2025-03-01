package ru.noxly.fuelseller.configs;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ConversionConfig {

    @Bean
    public DefaultConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        return service;
    }
}
