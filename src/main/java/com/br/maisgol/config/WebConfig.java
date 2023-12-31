package com.br.maisgol.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Permitir requisições de qualquer origem. Você pode especificar as origens permitidas conforme necessário.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*"); // Headers permitidos
    }
}
