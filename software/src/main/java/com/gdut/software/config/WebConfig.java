package com.gdut.software.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // .addowedOrigins("http://yourBrowserIp:vuePort","null")
                .allowedOrigins("http://172.23.116.70:8080", "null")
                .allowedOrigins("http://172.23.121.73:8080", "null")
                .allowedMethods("GET", "POST", "PUT", "OPTION", "DELETE")
                .maxAge(3600);
    }
}
