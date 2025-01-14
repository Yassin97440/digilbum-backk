package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${app.api-key}")
    private String apiKey;

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    // Getters ou utilisation directe dans d'autres composants
}