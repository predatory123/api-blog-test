package com.spring.blog.service;

import com.spring.blog.dao.pojo.SysUser;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.params.LoginParams;

/**
 * @author wyj
 * @date 2021/8/16 16:26
 */
public interface LoginService {

    /**
     * 登录功能
     * @param loginParams
     * @return
     */
    Result login(LoginParams loginParams);

    SysUser checkToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 用户注册
     * @param loginParams
     * @return
     */
    Result register(LoginParams loginParams);
}

