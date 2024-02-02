package com.yilan.awesome.feign;

import com.yilan.awesome.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author： yilan0916
 * @date : 2024/1/20
 */
@RequestMapping("/user")
public interface UserFeignApi {

    @GetMapping("/{id}")
    String findById(@PathVariable("id") Long id);

}
