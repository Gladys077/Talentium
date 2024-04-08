package cohorte16.homeservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(
                new Info()
                        .title("Prueba de OPEN API")
                        .description("Documentacion de Talentium API")
                        .version("1.0")
                        .license(new License().name("Apache 2.0"))
        );
    }

}
