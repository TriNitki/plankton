package ru.trinitki.shift.intensive.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Планктоон")
                        .description("Привет я описание")
                        .version("v2.2.8")
                        .license(new License().name("Лицензия").url("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
                );
    }
}
