package com.spring.blog.common.cache;

import java.lang.annotation.*;
/**
 * 缓存设置
 * @author wyj
 * @date 2021/9/27 15:17
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    long expire () default 1 * 60 * 1000;

    // 缓存标识  key
    String name() default "";
}
