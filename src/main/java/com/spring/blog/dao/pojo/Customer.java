package com.spring.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;

/**
 * @author: wyj
 * @date: 2022/1/10 17:26
 */
@Data
public class Customer {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer sex;

    private String phone;

    private String email;

    private String gmtCreate;

    private String gmtModify;

    private Integer isDelete;
}
