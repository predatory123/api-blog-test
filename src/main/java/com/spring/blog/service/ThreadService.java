package com.spring.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.spring.blog.dao.mapper.ArticleMapper;
import com.spring.blog.dao.mapper.CommentMapper;
import com.spring.blog.dao.pojo.Article;
import com.spring.blog.dao.pojo.Comment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author wyj
 * @date 2021/8/23 14:16
 */
@Component
public class ThreadService {


    // 期待此操作在线程池中进行,不影响主线程
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        int viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts + 1);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, article.getId());
        // 设置一个 为了在多线程的环境下，线程安全
        updateWrapper.eq(Article::getViewCounts, viewCounts);
        // update article set view_count = 100 where view_count = 99 and id = 11;
        // 思考： 为什么这样写？
        articleMapper.update(articleUpdate, updateWrapper);

        try {
            Thread.sleep(5000);
            System.out.println("浏览次数更新完成了。。。。！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async("taskExecutor")
    public void updateArticleCommentCount(ArticleMapper articleMapper,CommentMapper commentMapper, Article article) {
        int commentCounts = article.getCommentCounts();
        Article articleUpdate = new Article();
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Comment::getArticleId, article.getId());
        int newComment = commentMapper.selectCount(queryWrapper);
        articleUpdate.setCommentCounts(newComment);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, article.getId());
        if (commentCounts != newComment) {
            articleMapper.update(articleUpdate, updateWrapper);
        }

    }
}
