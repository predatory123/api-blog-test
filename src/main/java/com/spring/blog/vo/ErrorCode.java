package com.spring.blog.vo;

/**
 * @author wyj
 * @date 2021/8/16 16:34
 */
public enum  ErrorCode {

    PARAMS_ERROR(10001,"参数错误"),
    ACCOUNT_PWD_NOT_EXITS(10002, "用户名或密码不存在"),
    TOKEN_ERROR(10003, "token不合法"),
    ACCOUNT_EXIST(10004,"账户已存在"),
    NO_PERMISSION(8001,"暂无权限"),
    SESSION_TIME_OUT(8002,"登陆超时"),
    NO_LOGIN(8003, "未登录"),
    USER_NAME_ERROR(9001,"用户名不能为空"),
    USER_PHONE_ERROR(9001,"手机号不能为空");

    private int code;
    private String msg;

    ErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
