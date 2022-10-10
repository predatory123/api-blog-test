package com.spring.blog.service;

import com.spring.blog.dao.pojo.Customer;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.params.CustomerParams;
import com.spring.blog.vo.params.UpdateParams;

import java.text.ParseException;

/**
 * @author: wyj
 * @date: 2022/1/10 17:33
 */
public interface CustomerService {

    Result findAll();

    Result findCustomerByID(UpdateParams updateParams);

    Result addCustomer(CustomerParams customerParams) throws ParseException;

    void save(Customer customer);

    Result modifyCustomer(CustomerParams customerParams) throws ParseException;

    Result deleteCustomer(UpdateParams updateParams);
}
