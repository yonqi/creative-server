package com.example.creative_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Разрешаем все пути
                .allowedOrigins("*") // Разрешаем все домены (для разработки)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешенные методы
                .allowedHeaders("*") // Разрешаем все заголовки
                .allowCredentials(false) // Для простоты пока false (если нет кук/авторизации)
                .maxAge(3600); // Время кеширования настроек CORS
    }
}