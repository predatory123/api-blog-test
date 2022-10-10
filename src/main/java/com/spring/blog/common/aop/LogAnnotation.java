package com.spring.blog.common.aop;

import java.lang.annotation.*;

/**
 * @author wyj
 * @date 2021/8/23 17:39
 */
// type 代表可以放在类上面  method  可以放在方法上
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";

    String operator() default "";

}
