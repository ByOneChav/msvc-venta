package com.example.perfulandia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
            .title("Microservicio de Gestión de Ventas - PERFULANDIA")
            .version("v5.6")
            .description("Microservicio responsable de registrar, consultar, actualizar y eliminar ventas, incluyendo la obtención de detalles asociados al usuario.")
        );
    }

}
