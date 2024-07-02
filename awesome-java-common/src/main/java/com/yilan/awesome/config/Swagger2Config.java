package com.yilan.awesome.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author： yilan0916
 * @date: 2024/6/27
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yilan.awesome.rest")) // 替换成你的 Controller 包路径
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Your API Title")
                .description("Your API Description")
                .contact(new Contact("zk666", "浙江省杭州市", "zk666@qq.com"))
                .version("1.0.0")
                .build();
    }

    /**
     * 解决springboot升到2.6.x之后，knife4j报错
     * 原文链接：https://gitee.com/xiaoym/knife4j/issues/I4JT89
     * https://blog.csdn.net/privateobject/article/details/126480478
     * @param wes        the web endpoints supplier
     * @param ses    the servlet endpoints supplier
     * @param ces the controller endpoints supplier
     * @param emt          the endpoint media types
     * @param cep      the cors properties
     * @param wep       the web endpoints properties
     * @param env                 the environment
     * @return the web mvc endpoint handler mapping
     */
    @Bean
    public WebMvcEndpointHandlerMapping webMvcEndpointHandlerMapping(WebEndpointsSupplier wes
            , ServletEndpointsSupplier ses, ControllerEndpointsSupplier ces, EndpointMediaTypes emt
            , CorsEndpointProperties cep, WebEndpointProperties wep, Environment env) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
        Collection<ExposableWebEndpoint> webEndpoints = wes.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(ses.getEndpoints());
        allEndpoints.addAll(ces.getEndpoints());
        String basePath = wep.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = shouldRegisterLinksMapping(wep, env, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, emt
                , cep.toCorsConfiguration(), new EndpointLinksResolver(
                allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }
    /**
     * shouldRegisterLinksMapping
     *
     * @param wep
     * @param env
     * @param basePath
     * @return
     */
    private boolean shouldRegisterLinksMapping(WebEndpointProperties wep, Environment env, String basePath) {
        return wep.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(env).equals(ManagementPortType.DIFFERENT));
    }
}
