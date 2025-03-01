package ru.noxly.fuelseller.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

    @Value("${services.main.url}")
    private String baseUrl;

    @Value("${services.main.timeOut}")
    private Integer timeOut;

    /*@Bean(WEB_CLIENT_MAIN_BEAN)
    public WebClient webClientSocket() {
        val httpClient = HttpClient.create().responseTimeout(ofMillis(timeOut));
        return WebClient
                .builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }*/
}
