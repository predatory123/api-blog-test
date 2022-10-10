package com.spring.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.spring.blog.dao.dos.Authors;
import com.spring.blog.dao.mapper.ArticleMapper;
import com.spring.blog.dao.mapper.SysUserMapper;
import com.spring.blog.dao.pojo.SysUser;
import com.spring.blog.service.ArticleService;
import com.spring.blog.service.LoginService;
import com.spring.blog.service.SysUserService;
import com.spring.blog.vo.ErrorCode;
import com.spring.blog.vo.LoginUserVo;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wyj
 * @date 2021/8/9 16:59
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private static final String slat = "123456Mszlu!@#$$";

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private LoginService loginService;

    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setNickName("张三");
        }
        return sysUser;
    }

    @Override
    public Result hotUsers(int limit) {
        List<Authors> authors = sysUserMapper.hotAuthors(limit);
//        sysUserMapper.selectPage(new PageDTO<Authors>(limit, 10), Wrappers.lambdaQuery(Authors.class).eq(Authors::getCount, 1))
        return Result.success(authors);
    }

    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        String xxefs = DigestUtils.md5Hex(password + slat);
        System.out.println(xxefs);
        queryWrapper.eq(SysUser::getPassword, DigestUtils.md5Hex(password + slat));
        queryWrapper.select(SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickName);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public UserVo findUserVoById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setId(222L);
            sysUser.setAvatar("https://gw.alicdn.com/imgextra/i3/O1CN01FvVJVZ1SCgO7LPgOT_!!6000000002211-0-tps-450-288.jpg");
            sysUser.setNickName("张三");
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(sysUser, userVo);
        return userVo;
    }

    @Override
    public Result findUserByToken(String token) {
        /**
         * 1.token合法性校验
         *      是否为空，是否解析成功，redis是否存在
         * 2.如果失败，返回错误
         * 3.如果成功，返回对应的结果  LoginUserVo
         */
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null){
            Result.fail(ErrorCode.TOKEN_ERROR.getCode(),ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(sysUser.getId());
        loginUserVo.setNickName(sysUser.getNickName());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setAccount(sysUser.getAccount());
        return Result.success(sysUser);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account);
        queryWrapper.last("limit 1");
        return this.sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public void save(SysUser sysUser) {
        // 保存用户的时候 id会自动生成
        // 默认生成的id是分布式id， 采用雪花算法
        this.sysUserMapper.insert(sysUser);
    }
}
