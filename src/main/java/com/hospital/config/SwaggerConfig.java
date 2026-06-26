package com.hospital.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI hospitalOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Hospital Management System API")
                        .description("Spring Boot REST API for Hospital Management System")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Samir Sindhimeshram")
                                .email("samirmeshram47@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://github.com/"));
    }

}