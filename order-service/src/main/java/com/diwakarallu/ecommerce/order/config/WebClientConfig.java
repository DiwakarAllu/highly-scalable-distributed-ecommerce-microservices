package com.diwakarallu.ecommerce.order.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.micrometer.observation.ObservationRegistry;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
    HttpClient httpClient = HttpClient.create()
            .responseTimeout(Duration.ofSeconds(3));
    // HTTP timeout = I will not wait longer than 3 seconds for the HTTP response.

    @Value("${inventory.url}")
    private String inventoryUrl;
    @Autowired
    private ObservationRegistry observationRegistry;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(inventoryUrl)
                .observationRegistry(observationRegistry)
                .build();
    }
}
