package com.spring.blog.utils;

import com.spring.blog.dao.pojo.SysUser;

/**
 * @author wyj
 * @date 2021/8/20 17:04
 */
public class UserThreadLocal {

    private UserThreadLocal(){
    }

    // 线程变量隔离
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }

    public static SysUser get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }


}
