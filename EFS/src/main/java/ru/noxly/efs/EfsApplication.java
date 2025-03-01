package ru.noxly.efs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity(securedEnabled = true)
@SpringBootApplication(scanBasePackages = {
		"ru.noxly.efs",    // Пакет вашего сервиса
		"ru.noxly.validation",    // Пакет библиотеки
})
public class EfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EfsApplication.class, args);
	}

}
