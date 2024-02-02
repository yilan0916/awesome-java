package com.yilan.awesome.rest;

import com.yilan.awesome.feign.UserFeignApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author： yilan0916
 * @date : 2024/1/17
 */
@Component
@FeignClient("user-service")
public interface UserFeignService extends UserFeignApi {
//    @GetMapping(value = "/api/user/test")
//    String test();
//    @GetMapping("/api/user")
//    ResponseEntity<?> findAll();
}
