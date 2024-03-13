package org.github.snambi.restauth;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ApplicationConfig {

    @Bean(name = "googleAddressRestClient")
    public RestTemplate getGoogleAddressRestTemplate(RestTemplateBuilder builder){
        return builder.setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(10000))
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("REST API")
                        .description("Some custom description of API.")
                        .version("1.0")
                        .contact(new Contact().name("Nambi Sankaran").email( "www.github.com").url("snambi@gmail.com"))
                        .license(new License().name("License of API").url("API license URL")));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
