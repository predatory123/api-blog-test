package com.spring.blog.handler;

import com.spring.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wyj
 * @date 2021/8/10 11:22
 */
// 对加了 @Controller 注解的方法进行拦截处理  AOP的实现
@ControllerAdvice
public class AllExceptionHandler {

    // 进行异常处理，处理 Exception.class 的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回json数据
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(-999,"系统异常");
    }
}
