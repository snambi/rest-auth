package org.github.snambi.restauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        /*  following code is based on,
         *  https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html#oauth2resourceserver-jwt-sansboot
         */


        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                (authzRequests) ->
                        authzRequests
                                .requestMatchers("/search/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/create/**")
                                .permitAll()
                                .requestMatchers("/error/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/actuator/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
        );

        http.oauth2ResourceServer( (oauth2) -> oauth2.jwt(Customizer.withDefaults()));

        http.cors(x -> x.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.PUT.name(),
                HttpMethod.POST.name(),
                HttpMethod.DELETE.name()
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
        return source;
    }
}
