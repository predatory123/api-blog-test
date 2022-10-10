package com.spring.blog.service;

import com.spring.blog.vo.CategoryVo;
import com.spring.blog.vo.Result;
import org.springframework.stereotype.Service;

/**CategoryService
 * @author wyj
 * @date 2021/8/23 10:50
 */
public interface CategoryService {
    /**
     * 通过类别id查询类别
     * @param categoryId
     * @return
     */
    CategoryVo findCategoryById(Long categoryId);

    /**
     * 查询全部文章类别
     * @return
     */
    Result findAll();

    Result findAllDetail();

    Result findAllDetailById(Long id);
}
