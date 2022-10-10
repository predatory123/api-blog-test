package com.spring.blog.service;

import com.spring.blog.vo.Result;
import com.spring.blog.vo.TagVo;

import java.util.List;

/**
 * @author wyj
 * @date 2021/8/9 16:28
 */
public interface  TagService {

    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(int limit);

    /**
     * 查询所有的文章标签
     * @return
     */
    Result findAll();

    Result findAllDetail();

    Result findAllDetailById(Long id);
}
