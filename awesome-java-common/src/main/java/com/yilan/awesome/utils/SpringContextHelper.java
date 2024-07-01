package com.yilan.awesome.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author： yilan0916
 * @date: 2024/7/1
 */
@Component
public class SpringContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 0-2表示开发，测试，生产环境
     */
    private static int environ = 0;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHelper.applicationContext = applicationContext;

        //获取当前的系统环境
        Environment evn = applicationContext.getEnvironment();
        String[] activeProfiles = evn.getActiveProfiles();
        for (String profile : activeProfiles) {
            if ("dev".equals(profile)) {
                break;
            } else if ("test".equals(profile)) {
                environ = 1;
                break;
            } else if ("prod".equals(profile)) {
                environ = 2;
                break;
            }
        }

    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> baseType){
        return applicationContext.getBeansOfType(baseType);
    }

    /**
     * 当前是否为开发环境
     */
    public static boolean isDev() {
        return environ == 0;
    }

    /**
     * 是否为测试环境
     */
    public static boolean isTest() {
        return environ == 1;
    }

    /**
     * 是否为生产环境
     */
    public static boolean isProd() {
        return environ == 2;
    }

    /**
     * 获取当前环境
     */
    public static int getEnviron() {
        return environ;
    }

}
