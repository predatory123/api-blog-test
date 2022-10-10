package com.spring.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.blog.dao.dos.Archives;
import com.spring.blog.dao.dos.Authors;
import com.spring.blog.dao.mapper.ArticleBodyMapper;
import com.spring.blog.dao.mapper.ArticleMapper;
import com.spring.blog.dao.mapper.ArticleTagMapper;
import com.spring.blog.dao.mapper.CommentMapper;
import com.spring.blog.dao.pojo.*;
import com.spring.blog.service.*;
import com.spring.blog.utils.UserThreadLocal;
import com.spring.blog.vo.ArticleBodyVo;
import com.spring.blog.vo.ArticleVo;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.TagVo;
import com.spring.blog.vo.params.ArticleParams;
import com.spring.blog.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章列表
 * @author wyj
 * @date 2021/8/9 14:59
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private CommentMapper commentMapper;

//    @Override
//    public Result listAaticle(PageParams pageParams) {
//        // 分页查询 article 数据库
//        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
//        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        if (pageParams.getCategoryId() != null){
//            queryWrapper.eq(Article::getCategoryId, pageParams.getCategoryId());
//        }
//        List<Long> articleIdList = new ArrayList<>();
//        if (pageParams.getTagId() != null){
//            // 加入标签条件查询，article表中没有tag字段
//            // article_tag   ==》 article_id 1对多关系  tag_id
//            LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
//            articleTagLambdaQueryWrapper.eq(ArticleTag::getTagId, pageParams.getTagId());
//            List<ArticleTag> articleTags = articleTagMapper.selectList(articleTagLambdaQueryWrapper);
//            for (ArticleTag articleTag : articleTags) {
//                articleIdList.add(articleTag.getArticleId());
//            }
//            if (articleIdList.size() > 0){
//                // and in(1,2,3......)
//                queryWrapper.in(Article::getId,articleIdList);
//            }
//        }
//        // 按照置顶排序
////        queryWrapper.orderByDesc(Article::getWeight);
//        // order by createDate desc
//        queryWrapper.orderByDesc(Article::getWeight,Article::getViewCounts, Article::getCreateDate);
//        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
//        List<Article> records = articlePage.getRecords();
//        // 不能直接返回
//        List<ArticleVo> articleVoList = copyList(records,true,true);
//        return Result.success(articleVoList);
//    }

    @Override
    public Result listAaticle(PageParams pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        IPage<Article> articleIPage = articleMapper.listArticle(
                page,
                pageParams.getCategoryId(),
                pageParams.getTagId(),
                pageParams.getYear(),
                pageParams.getMonth()
        );
        List<Article> records = articleIPage.getRecords();
        return Result.success(copyList(records,true,true));
    }

    @Override
    public Result hotAaticle(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getViewCounts);
        queryWrapper.select(Article::getId, Article::getTitle, Article::getCreateDate);
        queryWrapper.last("limit " + limit);
        // select id,title from article order by view_counts desc limit 5
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles,false,false));
    }

    @Override
    public Result newArticles(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate);
        queryWrapper.select(Article::getId, Article::getTitle, Article::getCreateDate);
        queryWrapper.last("limit " + limit);
        // select id,title from article order by create_date desc limit 5
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles,false,false));
    }

    @Override
    public Result listArchives() {
        List<Archives> archivesList = articleMapper.listArchives();
        return Result.success(archivesList);
    }

    @Autowired
    private ThreadService threadService;

    @Override
    public Result findArticleById(Long articleId) {
        /**
         * 1.根据文章id 查询文章内容
         * 2.根据bodyId和categoryId 去做关联查询
         */
        Article article = this.articleMapper.selectById(articleId);
        ArticleVo articleVo = copy(article, true, true,true,true);
        // 查看文章后，增加阅读次数 ？
        // 文章查看后就直接返回数据了，此时做了一个更新操作，更新时加了写锁，阻塞其他的操作，性能比较低
        // 更新 增加了此次接口的耗时    更新时不能影响查看操作
        // 线程池  可以把更新操作放在线程池中去执行，不影响主线程
        threadService.updateArticleViewCount(articleMapper, article);
//        threadService.updateArticleCommentCount(articleMapper, commentMapper,article);

        return Result.success(articleVo);
    }

    @Override
    public Result publish(ArticleParams articleParams) {
        /**
         * 1.发布文章，构建article对象
         * 2.作者id 当前登录用户
         * 3.标签要将标签加入到关联列表
         * 4.内容存储
         *
         */
        // 获取当前登录用户，需要加入到登录拦截器中
        SysUser sysUser = UserThreadLocal.get();
        Article article = new Article();
        article.setAuthorId(sysUser.getId());
        article.setWeight(Article.Article_Common);
        article.setViewCounts(0);
        article.setTitle(articleParams.getTitle());
        article.setSummary(articleParams.getSummary());
        article.setCommentCounts(0);
        article.setCreateDate(System.currentTimeMillis());
        article.setCategoryId(articleParams.getCategory().getId());
        // 插入之后，会生成一个文章id
        this.articleMapper.insert(article);
        // tag
        List<TagVo> tags = articleParams.getTags();
        if (tags != null){
            for (TagVo tag : tags){
                Long articleId = article.getId();
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTagId(tag.getId());
                articleTag.setArticleId(articleId);
                articleTagMapper.insert(articleTag);
            }
        }
        // body
        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(article.getId());
        articleBody.setContent(articleParams.getBody().getContent());
        articleBody.setContentHtml(articleParams.getBody().getContentHtml());
        articleBodyMapper.insert(articleBody);
        article.setBodyId(articleBody.getId());
        articleMapper.updateById(article);
        Map<String,String> map = new HashMap<>();
        map.put("id", article.getId().toString());
        return Result.success(map);
    }


    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag, isAuthor,false,false));
        }

        return articleVoList;
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag, isAuthor ,isBody, isCategory));
        }

        return articleVoList;
    }

    @Autowired
    private CategoryService categoryService;

    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory){
        // 拷贝相同类型
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        // 拷贝时间
//        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(article.getCreateDate());
        articleVo.setCreateDate(format);

        // 并不是所有的接口 都需要作者和标签信息
        if (isTag){
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if (isAuthor){
            Long articleId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(articleId).getNickName());
        }
        if (isBody){
            Long bodyId = article.getBodyId();
            articleVo.setBody(findArticleBodyById(bodyId));
        }
        if(isCategory){
            Long categoryId = article.getCategoryId();
            articleVo.setCategory(categoryService.findCategoryById(categoryId));
        }
        return articleVo;
    }

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    private ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }

}
