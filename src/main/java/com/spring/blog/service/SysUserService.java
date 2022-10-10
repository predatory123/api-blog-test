package com.spring.blog.service;

import com.spring.blog.dao.pojo.SysUser;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.UserVo;

/**
 * @author wyj
 * @date 2021/8/9 16:55
 */
public interface SysUserService {

    SysUser findUserById(Long id);

    Result hotUsers(int limit);

    SysUser findUser(String account, String password);

    UserVo findUserVoById(Long id);

    /**
     * 获取登录用户的用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);

    /**
     * 根据账户查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);

    /**
     * 保存用户
     * @param sysUser
     */
    void save(SysUser sysUser);
}
