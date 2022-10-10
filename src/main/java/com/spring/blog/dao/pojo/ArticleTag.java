package com.spring.blog.dao.pojo;

import lombok.Data;

/**
 * @author wyj
 * @date 2021/8/23 16:58
 */
@Data
public class ArticleTag {

    private  Long id;

    private Long articleId;

    private Long tagId;
}
