package com.yilan.awesome.service;

import com.yilan.awesome.RedisApplicationTest;
import com.yilan.awesome.doamin.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author： yilan0916
 * @date: 2024/7/7
 */
@Slf4j
public class UserServiceTest extends RedisApplicationTest {

    @Autowired
    private UserService userService;

    /**
     * 获取两次，查看日志验证缓存
     */
    @Test
    public void getTwice() {
        // 模拟查询id为1的用户
        User user1 = userService.get(1L);
        log.debug("【user1】= {}", user1);

        // 再次查询
        User user2 = userService.get(1L);
        log.debug("【user2】= {}", user2);
        // 查看日志，只打印一次日志，证明缓存生效
    }

    /**
     * 先存，再查询，查看日志验证缓存
     */
    @Test
    public void getAfterSave() {
        userService.saveOrUpdate(new User(4L, "测试中文"));
        User user = userService.get(4L);
        log.debug("【user】 = {}", user);
    }

    /**
     * 测试删除，查看redis是否存在缓存数据
     */
    @Test
    public void deleteUser() {
        // 查询一次，使redis中存在缓存数据
        userService.get(1L);
        // 删除，查看redis使是否存在缓存数据
        userService.delete(1L);
    }

    /**
     * 测试@Cacheable设置过期时间
     */
    @Test
    public void testTTL() {
        userService.getWithTTL(2L);

    }
}