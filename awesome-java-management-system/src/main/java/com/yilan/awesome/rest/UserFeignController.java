package com.yilan.awesome.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： yilan0916
 * @date : 2024/1/17
 */
@RestController
@RequiredArgsConstructor
public class UserFeignController{

    private final DiscoveryClient discoveryClient;
    private final UserFeignService userFeignService;

    @GetMapping("/user/feign")
    public Object feign() {
        String byId = userFeignService.findById(123L);
        System.out.println("feign");
        System.out.println(byId);
        return byId;
//        return userFeignService.findAll();
    }

}
