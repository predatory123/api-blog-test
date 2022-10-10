package com.spring.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.blog.dao.pojo.ProductModel;

import java.util.List;

/**
 * @author wyj
 * @date 2021/8/24 10:15
 */
public interface ProductMapper extends BaseMapper<ProductModel> {

    /**
     *查询商品
     *@return
     */
    List<ProductModel> list();

    /**
     *保存商品
     * @param productModel
     */
    void save(ProductModel productModel);

}
