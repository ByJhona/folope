package com.byjhona.folope.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todos os endpoints
                .allowedOrigins("http://localhost:4200") // URL do frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Métodos permitidos
    }
}
