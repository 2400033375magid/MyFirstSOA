package com.example.demo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI libraryAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("Library Management System API")

                        .description("Spring Boot REST API for Managing Books")

                        .version("Version 1.0")

                        .contact(new Contact()

                                .name("Magid")

                                .email("magid@example.com"))

                        .license(new License()

                                .name("Open Source")

                                .url("https://opensource.org/licenses/MIT")))

                .externalDocs(new ExternalDocumentation()

                        .description("Project Documentation")

                        .url("https://spring.io/projects/spring-boot"));
    }

}