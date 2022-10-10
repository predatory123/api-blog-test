package com.spring.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author wyj
 * @date 2021/8/23 10:15
 */
@Data
public class ArticleBody {

    @TableId(value = "id",type= IdType.AUTO)
    private Long id;

    private String content;

    private String contentHtml;

    private Long articleId;
}
