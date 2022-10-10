package com.spring.blog.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.spring.blog.dao.mapper.TagMapper;
import com.spring.blog.dao.pojo.Tag;
import com.spring.blog.service.TagService;
import com.spring.blog.vo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: wyj
 * @date: 2022/1/5 17:44
 */
@SpringBootTest(classes = BlogAppTest.class)
public class TagTest {

    @Autowired
    public TagMapper tagMapper;

    @Autowired
    public TagService tagService;

    @Test
    public void testFindAllTags(){
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getTagName);
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);
        System.out.println(tags);
    }

    @Test
    public void testFindTags(){
        Result allTags = tagService.findAll();
        System.out.println(allTags);
    }

    /**
     * 测试热门标签
     */
    @Test
    public void testHotTags(){
        Result hots = tagService.hots(6);
        System.out.println(hots);
    }
}
