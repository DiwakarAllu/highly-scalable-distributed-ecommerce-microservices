package com.diwakarallu.ecommerce.product.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

        @Bean
        public OpenAPI productServiceAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("Product Service API")
                                                .description("This is the REST API for Product Service")
                                                .version("v1.0.0")
                                                .contact(new Contact().name("Diwakar Allu")
                                                                .email("diwakar.allu.3435@gmail.com"))
                                                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                                .externalDocs(new ExternalDocumentation()
                                                .description("Product Service Wiki")
                                                .url("https://product-service.com/docs"))
                                .servers(List.of(
                                                new Server().url("http://localhost:8081")
                                                                .description("Local dev server"),
                                                new Server().url("https://api.product-service.com")
                                                                .description("Production server")));
        }

}