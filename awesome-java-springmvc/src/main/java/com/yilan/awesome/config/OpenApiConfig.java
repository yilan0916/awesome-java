package com.yilan.awesome.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author： yilan0916
 * @date: 2024/7/4
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                // 接口文档标题
                .info(new Info().title("Knife4j OpenApi 3")
                        // 接口文档描述
                        .description("Knife4j OpenApi 3 example application")
                        // 接口文档版本
                        .version("v1.0")
                        // 开发者联系方式
                        .contact(new Contact().name("yilan0916").url("https://github.com/yilan0916")))
                .externalDocs(new ExternalDocumentation()
                        // 额外补充说明
                        .description("Github example code")
                        // 额外补充链接
                        .url("https://github.com/yilan0916/awesome-java"));
    }

}
