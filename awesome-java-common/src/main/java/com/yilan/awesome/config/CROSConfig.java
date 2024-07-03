package com.yilan.awesome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author： yilan0916
 * @date: 2024/7/1
 */
@Configuration
public class CROSConfig {

    /**
     * 支持跨域
     * @return WebMvcConfigurer
     */
//    @Bean
//    public WebMvcConfigurer configuration() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOriginPatterns(ALL)
//                        .allowedMethods(ALL)
//                        .allowedHeaders(ALL)
//                        .allowCredentials(true);
//            }
//        };
//    }
}
