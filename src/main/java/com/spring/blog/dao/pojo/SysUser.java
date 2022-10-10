package com.spring.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author wyj
 * @date 2021/8/9 14:34
 */
@Data
public class SysUser {

    @TableId(type = IdType.AUTO) // 设置id自增
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickName;

    private String password;

    private String salt;

    private String status;

}
