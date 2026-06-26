package com.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	// http://localhost:8080/swagger-ui/index.html#/
    @Bean
    public OpenAPI hospitalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hospital Management System API")
                        .description("Hospital Management System using Spring Boot, JPA, MySQL, JWT Authentication")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Samir Sindhimeshram")
                                .email("samirmeshram47@gmail.com")))
                
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://github.com/"));

    }

}