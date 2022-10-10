package com.spring.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.spring.blog.dao.pojo.SysUser;
import com.spring.blog.service.LoginService;
import com.spring.blog.service.SysUserService;
import com.spring.blog.utils.JWTUtils;
import com.spring.blog.vo.ErrorCode;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.params.LoginParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wyj
 * @date 2021/8/16 16:29
 */
@Service
@Transactional  // 加上事务
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private static final String slat = "123456Mszlu!@#$$";

    @Override
    public Result login(LoginParams loginParams) {
        /**
         * 1.检查参数书否合法
         * 2.查询用户名和密码是否存在
         * 3.不存在，登录失败报错
         * 4.存在，使用jwt 生成token 返回给前端
         * 5.token放入redis redis token： user信息，设置过期时间
         * （登陆认证时，先认证token字段是否合法，去redis认证是否存在）
         */
        String account = loginParams.getAccount();
        String password = loginParams.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
//        password = DigestUtils.md5Hex(password + slat);
        SysUser sysUser = sysUserService.findUser(account,password);
        if (sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXITS.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXITS.getMsg());
        }
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        if (StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap == null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }

    @Override
    public Result register(LoginParams loginParams) {
        /**
         * 1.判断参数是否合法
         * 2.判断账号
         * 3.不存在，注册用户
         * 4.生成token
         * 5.存入redis，并返回
         * 6.注意，加上事务，一旦中间的注册出现问题，需要回滚
         */
        String account = loginParams.getAccount();
        String password = loginParams.getPassword();
        String nickName = loginParams.getNickName();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password) || StringUtils.isBlank(nickName)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = sysUserService.findUserByAccount(account);
        if(sysUser != null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),"账户已经注册过了！");
        }

        sysUser = new SysUser();
        sysUser.setNickName(nickName);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password + slat));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("https://gw.alicdn.com/imgextra/i3/O1CN01hAtPKr1QpUijPEz0V_!!6000000002025-0-tps-450-288.jpg");
        sysUser.setAdmin(1);
        sysUser.setDeleted(0);
        sysUser.setSalt("");
        sysUser.setEmail("");
        this.sysUserService.save(sysUser);

        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }

}
