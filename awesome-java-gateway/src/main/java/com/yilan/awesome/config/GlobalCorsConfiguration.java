package com.yilan.awesome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 全局跨域配置
 *
 * @author： yilan0916
 * @date： 2024/1/31
 */
@Configuration
public class GlobalCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        //配置跨域
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        //允许哪些请求来源
        config.addAllowedOrigin("*");
        //是否允许携带cookie
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
