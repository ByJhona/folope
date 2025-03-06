package com.byjhona.folope.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain cadeiaFiltros(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (authorize) -> {
                            authorize.requestMatchers("/seguranca/publico").permitAll();
                            authorize.anyRequest().authenticated();
                        })
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
