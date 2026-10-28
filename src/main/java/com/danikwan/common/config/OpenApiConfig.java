package com.danikwan.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI danikwanOpenApi() {
        return new OpenAPI().info(new Info()
                .title("DANIKwan API")
                .description("Gestion des clubs, pratiquants, examens et compétitions de l'U.T.D.A.")
                .version("0.1.0"));
    }
}