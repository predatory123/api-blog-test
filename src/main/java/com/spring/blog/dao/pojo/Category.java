package com.spring.blog.dao.pojo;

import lombok.Data;

/**
 * @author wyj
 * @date 2021/8/23 10:25
 */
@Data
public class Category {

    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}
