package com.spring.blog.controller;

import com.spring.blog.dao.pojo.SysUser;
import com.spring.blog.utils.UserThreadLocal;
import com.spring.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyj
 * @date 2021/8/6 17:48
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test() {
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}
