package com.spring.blog.vo;

import lombok.Data;

/**
 * @author wyj
 * @date 2021/8/17 15:25
 */
@Data
public class LoginUserVo {

    private Long id;

    private String account;

    private String nickName;

    private String avatar;
}
