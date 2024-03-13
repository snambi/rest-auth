package org.github.snambi.restauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        // .csrf().httpBasic are needed for POST requests to work
//        //
//        //http.csrf(AbstractHttpConfigurer::disable);
////        http.authorizeHttpRequests(
////                (authzRequests) ->
////                        authzRequests
////                                .requestMatchers("/search/**")
////                                .permitAll()
////                                .requestMatchers(HttpMethod.POST, "/create/**")
////                                .permitAll()
////                                .requestMatchers("/error/**")
////                                .permitAll()
////        );
//
//
//        http.cors(x -> x.configurationSource(corsConfigurationSource()));
//
//        return http.build();
//    }

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
