package com.spring.blog.test;

import com.spring.blog.BlogApp;
import com.spring.blog.dao.mapper.ArticleMapper;
import com.spring.blog.dao.mapper.TagMapper;
import com.spring.blog.dao.pojo.SysUser;
import com.spring.blog.dao.pojo.Tag;
import com.spring.blog.service.ArticleService;
import com.spring.blog.service.SysUserService;
import com.spring.blog.service.TagService;
import com.spring.blog.service.impl.ArticleServiceImpl;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.params.PageParams;
import lombok.experimental.Delegate;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author wyj
 * @date 2021/8/10 11:50
 */
@SpringBootTest(classes = BlogAppTest.class)
public class HotArticleTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private TagMapper tagMapper;

    /**
     * 测试文章列表
     */
    @Test
    public void testAllArticle(){
        Result result = articleService.listAaticle(new PageParams());
        System.out.println(result);
//        List<Tag> tags = tagMapper.selectList(null);
//        System.out.println(tags);
    }




    /**
     * 测试文章归档
     */
    @Test
    public void testArticleArchives(){
        Result result = articleService.listArchives();
        System.out.println(result);
    }

//    @Test
    public void testTime(){
        String time = "2021-08-11 16:54";
        String dateTime = DateTime.now().toString("yyyy-MM-dd HH:dd");
        System.out.println(dateTime);
    }
}
