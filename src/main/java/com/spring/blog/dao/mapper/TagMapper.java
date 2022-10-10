package com.spring.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.blog.dao.pojo.Tag;

import java.util.List;

/**
 * @author wyj
 * @date 2021/8/9 14:38
 */
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章查询 标签列表
     * @param articleId
     * @return
     */
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 查询最热的标签，前n条
     * @param limit
     * @return
     */
    List<Long> findHotsTagIds(int limit);

    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
