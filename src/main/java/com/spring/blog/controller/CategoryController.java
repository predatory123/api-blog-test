package com.spring.blog.controller;

import com.spring.blog.service.CategoryService;
import com.spring.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyj
 * @date 2021/8/23 16:23
 */
@RestController
@RequestMapping("categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询全部文章分类
     * @return
     */
    @GetMapping
    public Result categories(){
        return categoryService.findAll();
    }

    /**
     * 查询文章分类详细数据
     * @return
     */
    @GetMapping("detail")
    public Result categoriesDetail(){
        return categoryService.findAllDetail();
    }

    /**
     * 查询某个文章分类的数据
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    public Result categoriesDetailById(@PathVariable("id") Long id){
        return categoryService.findAllDetailById(id);
    }
}
