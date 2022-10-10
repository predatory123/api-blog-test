package com.spring.blog.controller;

import com.spring.blog.dao.pojo.Customer;
import com.spring.blog.dao.pojo.SysUser;
import com.spring.blog.service.CustomerService;
import com.spring.blog.utils.UserThreadLocal;
import com.spring.blog.vo.CustomerVo;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.params.CustomerParams;
import com.spring.blog.vo.params.UpdateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

/**
 * @author: wyj
 * @date: 2022/1/10 17:30
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/findAll")
    public Result findAll(){
        return customerService.findAll();
    }

    @PostMapping("/detail")
    public Result findCustomerById(@RequestBody UpdateParams updateParams){
        return customerService.findCustomerByID(updateParams);
    }

    @PostMapping("/add")
    public Result addCustomer(@RequestBody CustomerParams customerParams) throws ParseException {
        return customerService.addCustomer(customerParams);
    }

    @PostMapping("/modify")
    public Result modifyCustomer(@RequestBody CustomerParams customerParams) throws ParseException {
        return customerService.modifyCustomer(customerParams);
    }

    @PostMapping("/delete")
    public Result deleteCustomer(@RequestBody UpdateParams updateParams){
        return customerService.deleteCustomer(updateParams);
    }



}
