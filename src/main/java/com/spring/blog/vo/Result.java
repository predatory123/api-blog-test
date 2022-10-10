package com.spring.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 定义返回结果页面
 * @author wyj
 * @date 2021/8/9 14:45
 */
@Data
@AllArgsConstructor // 自动生成构造
public class Result {

    private boolean success;

    private int code;

    private String msg;

    private Object data;

    /**
     * 定义成功页面返回值
     * @param data
     * @return
     */
    public static Result success(Object data){
        return new Result(true,200,"success",data);
    }

    /**
     * 定义失败页面返回值
     * @param code
     * @param msg
     * @return
     */
    public static Result fail(int code, String msg){
        return new Result(false, code, msg,null);
    }
}
