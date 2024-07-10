package com.yilan.awesome.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author： yilan0916
 * @date: 2024/7/10
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.yilan.awesome.annotation.ApiLog)")
    public void logPointcut() {
    }


    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param pjp join point for advice
     */
    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //开始打印请求日志
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //打印请求相关参数
        log.info("================== start ==================");
        //打印请求 url
        log.info("URL              : {}", request.getRequestURL().toString());
        //打印描述信息
        //打印HTTP method
        log.info("HTTP Method      : {}", request.getMethod());
        //打印调用 controller 的全路径以及执行方法
        log.info("Class Method     : {}.{}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        //打印请求的ip
        log.info("IP               : {}", request.getRemoteAddr());
        //打印请求入参
        log.info("Request Args     : {}", pjp.getArgs());

        long startTime = System.currentTimeMillis();
        // 执行切点
        Object result = pjp.proceed();
        // 打印出参
        log.info("Response Args    : {}", result);
        // 执行耗时
        log.info("Time-Consuming   : {} ms", System.currentTimeMillis() - startTime);
        log.info("================== end ===================" + System.lineSeparator());
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {

    }


}
