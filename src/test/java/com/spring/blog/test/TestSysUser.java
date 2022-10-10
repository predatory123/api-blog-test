package com.spring.blog.test;

import com.spring.blog.dao.mapper.SysUserMapper;
import com.spring.blog.dao.pojo.SysUser;
import com.spring.blog.service.SysUserService;
import com.spring.blog.vo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: wyj
 * @date: 2022/1/10 16:34
 */
@SpringBootTest
public class TestSysUser {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testFindAllUser(){
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        sysUsers.forEach(System.out::println);
//        for (int i = 0; i < sysUsers.size(); i++) {
//            System.out.println(sysUsers.get(i));
//        }
    }

    /**
     * 测试最热作者
     */
    @Test
    public void testHotAuthor(){
        Result result = sysUserService.hotUsers(3);
        System.out.println(result);
    }
}
