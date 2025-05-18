//package com.movieshub.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // Apply to all endpoints
//                        .allowedOrigins("http://localhost:4200") // Allow requests from your Angular app
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
//                        .allowedHeaders("*") // Allow all headers
//                        .allowCredentials(true) // If you need to handle cookies or authorization headers
//                        .maxAge(3600); // Cache duration for CORS preflight requests (in seconds)
//            }
//        };
//    }
//}
