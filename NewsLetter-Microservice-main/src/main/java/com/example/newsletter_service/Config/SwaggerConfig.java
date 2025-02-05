package com.example.newsletter_service.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Newsletter Service API")
                        .version("1.0")
                        .description("API pour gérer les newsletters et les abonnements")
                        .contact(new Contact()
                                .name("YowYob")
                                .email("contact@yowyob.com")));
    }
}


