package com.digilbum.app.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Add the origin of your Nuxt application
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Add the allowed HTTP methods
                .allowedHeaders("*") // Add the allowed headers
                .allowCredentials(true)
                .maxAge(3600); // Allow credentials (e.g., cookies, authorization headers)
    }
}

