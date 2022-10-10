package com.spring.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.spring.blog.dao.mapper.CustomerMapper;
import com.spring.blog.dao.pojo.Customer;
import com.spring.blog.dao.pojo.SysUser;
import com.spring.blog.service.CustomerService;
import com.spring.blog.utils.JWTUtils;
import com.spring.blog.utils.UserThreadLocal;
import com.spring.blog.vo.CustomerVo;
import com.spring.blog.vo.ErrorCode;
import com.spring.blog.vo.Request;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.params.CustomerParams;
import com.spring.blog.vo.params.UpdateParams;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: wyj
 * @date: 2022/1/10 17:34
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerService customerService;

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Customer::getIsDelete,0);
        queryWrapper.orderByDesc(Customer::getGmtCreate);
        List<Customer> customers = customerMapper.selectList(queryWrapper);
        return Result.success(copyList(customers));
    }

    @Override
    public Result addCustomer(CustomerParams customerParams) throws ParseException {

        String customerName = customerParams.getName();
        Integer customerSex = customerParams.getSex();
        String customerPhone = customerParams.getPhone();
        String customerEmail = customerParams.getEmail();
        Date customerCreateTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(customerCreateTime);
        customerCreateTime = simpleDateFormat.parse(format);

        if (StringUtils.isBlank(customerName) ){
            return Result.fail(ErrorCode.USER_NAME_ERROR.getCode(),ErrorCode.USER_NAME_ERROR.getMsg());
        }else if(StringUtils.isBlank(customerPhone)){
            return Result.fail(ErrorCode.USER_PHONE_ERROR.getCode(),ErrorCode.USER_PHONE_ERROR.getMsg());
        }
        Customer customer = new Customer();
        customer.setName(customerName);
        customer.setSex(customerSex);
        customer.setPhone(customerPhone);
        customer.setEmail(customerEmail);
        customer.setGmtCreate(format);
        customer.setGmtModify(null);
        Integer is_del = 0;
        customer.setIsDelete(is_del);
        this.customerService.save(customer);
        return Result.success(null);
    }

    @Override

    public Result findCustomerByID(UpdateParams updateParams) {
        Long customerId = updateParams.getCustomerId();
        Customer customers = customerMapper.selectById(customerId);
        if (customers == null){
            return Result.fail(-9999,"数据不存在");
        }
        CustomerVo customerVo = copy(customers);
        return Result.success(customerVo);

    }


    @Override
    public Result modifyCustomer(CustomerParams customerParams) throws ParseException {
        Long customerId = customerParams.getCustomerId();
        Customer customer = customerMapper.selectById(customerId);
        String customerName = customerParams.getName();
        Integer customerSex = customerParams.getSex();
        String customerPhone = customerParams.getPhone();
        String customerEmail = customerParams.getEmail();
        // 获取当前日期，转换为数据库格式
        Date customerModifyTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(customerModifyTime);
        customerModifyTime = sdf.parse(format);

        if (StringUtils.isBlank(customerName) ){
            return Result.fail(ErrorCode.USER_NAME_ERROR.getCode(),ErrorCode.USER_NAME_ERROR.getMsg());
        }else if(StringUtils.isBlank(customerPhone)){
            return Result.fail(ErrorCode.USER_PHONE_ERROR.getCode(),ErrorCode.USER_PHONE_ERROR.getMsg());
        }
        customer.setName(customerName);
        customer.setSex(customerSex);
        customer.setPhone(customerPhone);
        customer.setEmail(customerEmail);
        customer.setGmtModify(format);
        customerMapper.updateById(customer);
        return Result.success(null);
    }

    @Override
    public Result deleteCustomer(UpdateParams updateParams) {
        Long customerId = updateParams.getCustomerId();
        Customer customer = customerMapper.selectById(customerId);
        if (customer == null){
            return Result.fail(-9999,"数据不存在");
        }
        customer.setIsDelete(1);
        customerMapper.updateById(customer);
        return Result.success(null);
    }

    public CustomerVo copy(Customer customer){
        CustomerVo CustomerVo = new CustomerVo();
        BeanUtils.copyProperties(customer, CustomerVo);
        return CustomerVo;
    }

    public List<CustomerVo> copyList(List<Customer> record){
        List<CustomerVo> customerVoList = new ArrayList<>();
        for (Customer customer : record) {
            customerVoList.add(copy(customer));
        }
        return customerVoList;
    }

    @Override
    public void save(Customer customer) {
        customerMapper.insert(customer);
    }

}
