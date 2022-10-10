package com.spring.blog.service;

import com.spring.blog.vo.Result;
import com.spring.blog.vo.params.ArticleParams;
import com.spring.blog.vo.params.PageParams;

/**
 * @author wyj
 * @date 2021/8/9 14:56
 */
public interface ArticleService {

    /**
     * 分页查询，文章列表
     * @param pageParams
     * @return
     */
    Result listAaticle(PageParams pageParams);

    /**
     * 最热文章
     * @param limit
     * @return
     */
    Result hotAaticle(int limit);

    /**
     * 最新文章
     * @param limit
     * @return
     */
    Result newArticles(int limit);

    /**
     * 文章归档
     * @return
     */
    Result listArchives();

    /**
     * 查看文章详情
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);

    /**
     * 文章发布服务
     * @param articleParams
     * @return
     */
    Result publish(ArticleParams articleParams);
}
