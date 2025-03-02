package ru.noxly.fuelseller.configs;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

import java.io.IOException;

@Configuration
public class FtpConfig {

    @Bean
    public DefaultFtpSessionFactory ftpSessionFactory() throws IOException {
        DefaultFtpSessionFactory factory = new DefaultFtpSessionFactory();
        factory.setHost("185.204.2.233");
        factory.setPort(21);
        factory.setUsername("user");
        factory.setPassword("123");
        factory.setClientMode(2);
        factory.getSession().getClientInstance().enterLocalPassiveMode();
        factory.getSession().getClientInstance().setUseEPSVwithIPv4(true);

        factory.getSession().getClientInstance().setControlEncoding("UTF-8");

        factory.getSession().getClientInstance().changeWorkingDirectory("/");
        return factory;
    }
}
