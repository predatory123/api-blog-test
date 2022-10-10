package com.spring.blog.vo.params;

import lombok.Data;

/**
 * @author wyj
 * @date 2021/8/16 16:27
 */
@Data
public class LoginParams {

    private String account;

    private String password;

    private String nickName;
}
