package com.yilan.awesome.system;

import com.yilan.awesome.domain.User;
import com.yilan.awesome.feign.UserFeignApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： yilan0916
 * @date : 2024/1/20
 */

@RestController
@RefreshScope //在控制器类加入@RefreshScope注解使当前类下的配置支持Nacos的动态刷新功能。
public class UserClientImpl implements UserFeignApi {

    @Value("${server.port}")
    private String serverPort;
    @Value("${config.info}")
    private String configInfo;
    @Override
    public String findById(Long id) {
        System.out.println("configInfo: " + configInfo);
        return "String" + id + "  serverPort" + serverPort;
    }
}
