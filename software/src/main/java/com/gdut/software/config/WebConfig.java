package com.gdut.software.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${CORS_ALLOWED_ORIGINS}")
    private String[] CORSAllowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
<<<<<<< HEAD
//                .allowedOrigins("http://192.168.1.102:8080", "null")
                .allowedOrigins(CORSAllowedOrigins)
=======
                .allowedOrigins("http://172.23.116.70:8080", "null")
                .allowedOrigins("http://192.168.1.102:8080", "null")
>>>>>>> 9d829d6 (做题分析界面增加)
                .allowedMethods("GET", "POST", "PUT", "OPTION", "DELETE")
                .maxAge(3600);
    }
}
