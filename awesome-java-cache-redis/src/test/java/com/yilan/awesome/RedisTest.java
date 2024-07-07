package com.yilan.awesome;

import com.yilan.awesome.doamin.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * <p>
 * Redis测试
 * </p>
 *
 * @author： yilan0916
 * @date: 2024/7/7
 */
@Slf4j
public class RedisTest extends RedisApplicationTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    /**
     * 测试 Redis 操作
     */
    @Test
    public void get() throws InterruptedException {
        // 测试线程安全，程序结束查看redis中count的值是否为1000
        stringRedisTemplate.opsForValue().set("count", String.valueOf(0));
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i ->
                executorService.execute(() ->
                        stringRedisTemplate.opsForValue().increment("count", 1)));

        Thread.sleep(5000);
        log.debug("count = {}", stringRedisTemplate.opsForValue().get("count"));
        stringRedisTemplate.opsForValue().set("k1", "v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.debug("【k1】= {}", k1);

        // 以下演示整合，具体Redis命令可以参考官方文档
        String key = "xkcoding:user:1";
        redisCacheTemplate.opsForValue().set(key, new User(1L, "user1"));
        // 对应 String（字符串）
        User user = (User) redisCacheTemplate.opsForValue().get(key);
        log.debug("【user】= {}", user);
    }
}
