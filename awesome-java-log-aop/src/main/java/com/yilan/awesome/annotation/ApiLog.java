package com.yilan.awesome.annotation;

import java.lang.annotation.*;

/**
 * @author： yilan0916
 * @date: 2024/7/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD})
@Documented
public @interface ApiLog {

    String value() default "";
    String description() default "";
}
