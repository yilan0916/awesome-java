package com.yilan.awesome.rest;

import com.yilan.awesome.annotation.ApiLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： yilan0916
 * @date: 2024/7/10
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @ApiLog
    @GetMapping("/get")
    public String get(Long id) {
        return "测试AOP打印日志功能, id:" + id;
    }
}
