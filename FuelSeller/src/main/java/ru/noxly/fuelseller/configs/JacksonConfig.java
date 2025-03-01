package ru.noxly.fuelseller.configs;

import com.fasterxml.jackson.databind.Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.SpringDataJacksonConfiguration;

@Configuration
public class JacksonConfig {
    @Bean
    public Module springDataPageModule() {
        return new SpringDataJacksonConfiguration().pageModule();
    }
}
