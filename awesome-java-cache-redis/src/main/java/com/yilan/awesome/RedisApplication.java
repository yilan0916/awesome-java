package com.yilan.awesome;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author： yilan0916
 * @date: 2024/7/6
 */
@Slf4j
@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(RedisApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(path)) {
            path = "";
        }
        String localURL = String.format("http://localhost:%s%s", port, path);
        String externalURL = String.format("http://%s:%s%s", ip, port, path);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local访问网址: \t\t" + localURL + "\n\t" +
                "External访问网址: \t" + externalURL + "\n" +
                "----------------------------------------------------------");
    }
}
