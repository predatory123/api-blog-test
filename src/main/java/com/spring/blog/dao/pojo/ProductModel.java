package com.spring.blog.dao.pojo;

import lombok.Data;

/**
 * @author wyj
 * @date 2021/8/24 10:03
 */
@Data
public class ProductModel {

    private Integer id;

    private String image;

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", image='" + image + '\'' +
                '}';
    }
}
