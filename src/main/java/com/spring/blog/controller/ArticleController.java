package com.spring.blog.controller;

import com.spring.blog.common.aop.LogAnnotation;
import com.spring.blog.service.ArticleService;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.params.ArticleParams;
import com.spring.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wyj
 * @date 2021/8/9 14:40
 */
// json格式数据交互
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 首页  文章列表
     * @param pageParams
     * @return
     */
    @PostMapping("list")
    // 加上该注解，代表对此功能追加日志记录
    @LogAnnotation(module="文章", operator="获取文章列表")
    public Result listArticle(@RequestBody PageParams pageParams){
//        int i = 10/0;
        return articleService.listAaticle(pageParams);
    }

    /**
     * 首页- 最热文章
     * @return
     */
    @PostMapping("hot")
    public Result hotArticle(){
        int limit = 5;
        return articleService.hotAaticle(limit);
    }

    /**
     * 首页- 最新文章
     * @return
     */
    @PostMapping("new")
    public Result newArticles(){
        int limit = 5;
        return articleService.newArticles(limit);
    }

    /**
     * 首页- 文章归档
     * @return
     */
    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }

    /**
     * 查看文章详情
     * @param articleId
     * @return
     */
    @PostMapping("/view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

    /**
     * 文章发布
     * @param articleParams
     * @return
     */
    @PostMapping("/publish")
    public Result publish(@RequestBody ArticleParams articleParams){
        return articleService.publish(articleParams);
    }

}
