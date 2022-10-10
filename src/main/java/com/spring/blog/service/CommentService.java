package com.spring.blog.service;

import com.spring.blog.vo.Result;
import com.spring.blog.vo.params.CommentParams;

/**
 * @author wyj
 * @date 2021/8/23 15:21
 */
public interface CommentService {

    /**
     * 根据文章id，查询所有的文章列表
     * @param id
     * @return
     */
    Result commentsByArticleId(Long id);

    /**
     * 创建评论
     * @param commentParams
     * @return
     */
    Result comment(CommentParams commentParams);
}
