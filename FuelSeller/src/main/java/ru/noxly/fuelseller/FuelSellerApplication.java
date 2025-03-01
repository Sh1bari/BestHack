package ru.noxly.fuelseller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = {
		"ru.noxly.fuelseller",    // Пакет вашего сервиса
		"ru.sh1bari.resolver",    // Пакет библиотеки
		"ru.noxly.validation",    // Пакет библиотеки
})
public class FuelSellerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuelSellerApplication.class, args);
	}

}
