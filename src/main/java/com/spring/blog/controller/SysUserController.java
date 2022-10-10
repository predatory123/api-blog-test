package com.spring.blog.controller;

import com.spring.blog.service.SysUserService;
import com.spring.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyj
 * @date 2021/8/12 14:31
 */
@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @PostMapping("hotUsers")
    public Result hotUsers(){
        int limit = 3;
        return sysUserService.hotUsers(limit);
    }
}
