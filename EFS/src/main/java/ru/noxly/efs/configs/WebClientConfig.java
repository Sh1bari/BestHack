package ru.noxly.efs.configs;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import static java.time.Duration.ofMillis;
import static ru.noxly.efs.common.Constants.WEB_CLIENT_AUTH_BEAN;
import static ru.noxly.efs.common.Constants.WEB_CLIENT_MAIN_BEAN;

@Configuration
public class WebClientConfig {

    @Value("${services.main.url}")
    private String mainBaseUrl;

    @Value("${services.main.timeOut}")
    private Integer mainTimeOut;

    @Value("${services.auth.url}")
    private String authBaseUrl;

    @Value("${services.auth.timeOut}")
    private Integer authTimeOut;

    @Bean(WEB_CLIENT_AUTH_BEAN)
    public WebClient authWebClientSocket() {
        val httpClient = HttpClient.create().responseTimeout(ofMillis(authTimeOut));
        return WebClient
                .builder()
                .baseUrl(authBaseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean(WEB_CLIENT_MAIN_BEAN)
    public WebClient mainWebClientSocket() {
        val httpClient = HttpClient.create().responseTimeout(ofMillis(mainTimeOut));
        return WebClient
                .builder()
                .baseUrl(mainBaseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
