package com.yilan.awesome;

import com.yilan.awesome.domain.entity.Order;
import com.yilan.awesome.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Junit的MockMvc功能和mp的枚举类
 *
 * @author： yilan0916
 * @date: 2024/7/9
 */
@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;

    @GetMapping("/getById")
    public Order getById(@RequestParam("id") Long id) {
        return orderMapper.selectById(id);
    }
}
