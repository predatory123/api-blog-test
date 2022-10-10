package com.spring.blog.test;

import com.spring.blog.service.CustomerService;
import com.spring.blog.vo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: wyj
 * @date: 2022/1/10 18:20
 */
@SpringBootTest
public class TestCustomer {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testFindAll(){
        Result all = customerService.findAll();
        System.out.println(all);
    }

//    @Test
//    public void testFindByCustomerId(){
//        Long id = 8L;
//        Result customerID = customerService.findCustomerByID(id);
//        System.out.println(customerID);
//
//    }
}
