package com.spring.blog.vo;

import java.io.Serializable;

/**
 * @author: wyj
 * @date: 2022/1/19 16:36
 */
public class Request<T>  {

    private T data;

    public Request() {
    }

    public Request(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
