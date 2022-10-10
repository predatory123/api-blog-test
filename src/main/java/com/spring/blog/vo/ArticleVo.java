package com.spring.blog.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;

/**
 * @author wyj
 * @date 2021/8/9 15:19
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleVo {

    private Long id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;

    // 创建时间
    private String createDate;

    private String author;

    private ArticleBodyVo body;

    private List<TagVo> tags;

    private CategoryVo category;

}
