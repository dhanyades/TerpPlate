package com.example.langchainapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints
            .allowedOrigins("http://127.0.0.1:5502", "http://127.0.0.1:8080", "http://127.0.0.1:5500") // Allow your frontend origin
            // Add the front end port here whatever it is. Look at port plugin.
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow desired HTTP methods
            .allowCredentials(true); // Optional: if you need to allow credentials
    }
}
