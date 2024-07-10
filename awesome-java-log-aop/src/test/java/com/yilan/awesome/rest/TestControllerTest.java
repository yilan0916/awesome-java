package com.yilan.awesome.rest;

import com.yilan.awesome.LogAopApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author： yilan0916
 * @date: 2024/7/10
 */
@AutoConfigureMockMvc
public class TestControllerTest extends LogAopApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    void test() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/test/get").param("id", "1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8")) // 设置字符编码为UTF-8)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}