package com.spring.blog.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wyj
 * @date 2021/8/6 15:33
 */
@SpringBootApplication(scanBasePackages = {"com.spring.blog"})
@MapperScan(basePackages = {"mapper"})
public class BlogAppTest {

    public static void main(String[] args) {
        SpringApplication.run(BlogAppTest.class, args);
    }
}
