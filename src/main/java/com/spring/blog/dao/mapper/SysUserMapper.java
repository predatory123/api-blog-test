package com.spring.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.blog.dao.dos.Authors;
import com.spring.blog.dao.pojo.SysUser;

import java.util.List;

/**
 * @author wyj
 * @date 2021/8/9 14:39
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<Authors> hotAuthors(int limit);
}
