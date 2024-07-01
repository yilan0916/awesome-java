package com.yilan.awesome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author： yilan0916
 * @date: 2024/7/1
 */
@EnableAsync
@Configuration
public class ThreadPoolConfig {

    @Bean("thread-pool-executor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(100);
        // 最大线程数
        taskExecutor.setMaxPoolSize(1000);
        // 阻塞队列长度
        taskExecutor.setQueueCapacity(1000);
        // 空闲线程最大存活时间
        taskExecutor.setKeepAliveSeconds(200);
        // 拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

}
