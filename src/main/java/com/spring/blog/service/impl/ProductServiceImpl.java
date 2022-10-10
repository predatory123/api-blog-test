package com.spring.blog.service.impl;

import com.spring.blog.dao.ProductDao;
import com.spring.blog.dao.mapper.ProductMapper;
import com.spring.blog.dao.pojo.ProductModel;
import com.spring.blog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wyj
 * @date 2021/8/24 10:09
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductModel> list() {
        return productMapper.list();
    }

    @Override
    public void save(ProductModel productModel) {
        productMapper.save(productModel);
    }
}
