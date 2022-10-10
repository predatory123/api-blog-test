package com.spring.blog.vo;

import lombok.Data;

/**
 * @author wyj
 * @date 2021/8/23 10:31
 */
@Data
public class CategoryVo {

    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}
