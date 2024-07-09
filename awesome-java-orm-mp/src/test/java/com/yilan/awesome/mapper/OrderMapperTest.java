package com.yilan.awesome.mapper;

import com.yilan.awesome.OrderController;
import com.yilan.awesome.domain.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author： yilan0916
 * @date: 2024/7/9
 */
@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void get() {
        Order order = orderMapper.selectById(1L);
        System.out.println(order);
    }

    @Test
    void insert() {
        Order order = new Order()
                .setName("insert————name").setStatus(Order.Status.RECEIVE);
        int insert = orderMapper.insert(order);
        assertEquals(insert, 1);
    }

    @Test
    void testController() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/order/getById").param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")) // 设置字符编码为UTF-8)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        log.info(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

}