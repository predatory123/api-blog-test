package com.spring.blog.handler;

import com.alibaba.fastjson.JSON;
import com.spring.blog.dao.pojo.SysUser;
import com.spring.blog.service.LoginService;
import com.spring.blog.utils.UserThreadLocal;
import com.spring.blog.vo.ErrorCode;
import com.spring.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wyj
 * @date 2021/8/18 14:53
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 1.在执行controller方法（Handler) 之前拦截
         * 2.需要判断请求的接口路径， 是否为HandlerMethod(controller方法)
         * 3.判断token是否为空，如果为空，未登录
         * 4.如果认证成功，放行
         */
        if (!(handler instanceof HandlerMethod)){
            // handler 可能是 RequestResourceHandler
            // SpringBoot 程序访问静态资源路径 默认 classpath下的static目录
            return true;
        }

        String token = request.getHeader("Authorization");
        log.info("------------------request start---------------------");
        String requestUrl = request.getRequestURI();
        log.info("request url:{}", requestUrl);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}",token);
        log.info("-----------------request end -----------------------");

        if (StringUtils.isBlank(token)){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        // 登录验证成功，放行
        // 在controller中获取用户信息
        UserThreadLocal.put(sysUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 如果不删除ThreadLocal中用完的信息，会有内存泄漏的风险
        UserThreadLocal.remove();
    }
}
