package com.spring.blog.vo.params;

import com.spring.blog.dao.pojo.Tag;
import com.spring.blog.vo.CategoryVo;
import com.spring.blog.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * @author wyj
 * @date 2021/8/23 16:39
 */
@Data
public class ArticleParams {

    private Long id;

    private ArticleBodyParams body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}
