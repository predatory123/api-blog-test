package com.spring.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.blog.dao.dos.Archives;
import com.spring.blog.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wyj
 * @date 2021/8/9 14:29
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<Archives> listArchives();

    IPage<Article> listArticle(Page<Article> page,
                               Long categoryId,
                               Long tagId,
                               String year,
                               String month);
}
