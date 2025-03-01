package ru.noxly.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = {
        "ru.noxly.authorization",    // Пакет вашего сервиса
        "ru.sh1bari.resolver",    // Пакет библиотеки
        "ru.noxly.validation",    // Пакет библиотеки
})
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

}
