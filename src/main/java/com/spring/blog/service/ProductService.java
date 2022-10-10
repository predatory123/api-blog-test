package com.spring.blog.service;

import com.spring.blog.dao.pojo.ProductModel;

import java.util.List;

/**
 * @author wyj
 * @date 2021/8/24 10:08
 */
public interface ProductService {

    /**
     * 查询商品
     * @return
     */
    List<ProductModel> list();

    /**
     * 保存商品
     * @param productModel
     */
    void save(ProductModel productModel);
}
