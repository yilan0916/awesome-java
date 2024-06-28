package com.yilan.awesome.rest;

import com.yilan.awesome.base.ResponseResult;
import com.yilan.awesome.domain.vo.UserDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author： yilan0916
 * @date: 2024/6/27
 */
@Api(tags = "demo应用：ValidationController")
@Slf4j
@Validated
@RestController
@RequestMapping("/validation")
@RequiredArgsConstructor
public class ValidationController {

    /**
     * 校验表单或实体类
     */

    @PostMapping("/save")
    public ResponseResult<UserDTO> save(@Validated @RequestBody UserDTO user) {
        return ResponseResult.success(user);
    }

    /**
     * 校验普通参数
     */
    @GetMapping("/test")
    public ResponseResult<?> test(@Length(min = 1, max = 10) @NotBlank String username) {
        System.out.println(username + "1");
        return ResponseResult.success();
    }

    @GetMapping("/test2")
    public ResponseResult<?> test2(@Min(200) Long username) {
        System.out.println(username + "1");
        return ResponseResult.success();
    }

    @GetMapping("/fun1/{userId}")
    public ResponseResult<?> fun1(@PathVariable @Min(1000L) Long userId) {
        System.out.println(userId);
        return ResponseResult.success();

    }

}