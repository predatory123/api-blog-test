package com.spring.blog.test;

import com.spring.blog.dao.mapper.ArticleMapper;
import com.spring.blog.dao.pojo.Article;
import com.spring.blog.service.ArticleService;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.params.PageParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: wyj
 * @date: 2022/1/10 16:52
 */
@SpringBootTest
public class TestArticle {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;

    @Test
    public void testFindAll(){
        List<Article> articleList = articleMapper.selectList(null);
        articleList.forEach(System.out::println);
    }

    /**
     * 测试文章列表
     */
    @Test
    public void testAllArticle(){
        Result result = articleService.listAaticle(new PageParams());
        System.out.println(result);
    }

    /**
     * 测试热门文章
     */
    @Test
    public void testHotArticle(){
        Result result = articleService.hotAaticle(5);
        System.out.println(result);
    }

    /**
     * 测试最新文章
     */
    @Test
    public void testNewArticle(){
        Result result = articleService.newArticles(5);
        System.out.println(result);
    }

    /**
     * 测试文章详情
     */
    @Test
    public void testArticleBody(){
        Result result = articleService.findArticleById(109L);
        System.out.println(result);
    }
}
