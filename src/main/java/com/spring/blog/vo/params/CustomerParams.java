package com.spring.blog.vo.params;

import lombok.Data;
import org.joda.time.DateTime;

/**
 * @author: wyj
 * @date: 2022/1/10 21:14
 */
@Data
public class CustomerParams {

    private Long customerId;

    private String name;

    private Integer sex;

    private String phone;

    private String email;

//    private DateTime gmtCreate;
//
//    private DateTime gmtModify;
}
