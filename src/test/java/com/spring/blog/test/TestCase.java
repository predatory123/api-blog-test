package com.spring.blog.test;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * @author wyj
 * @date 2021/10/19 14:48
 */

public class TestCase {
    @Test
    public void test01(){
        int a = 111,b = 222;
        assert a != 222;
    }

    @Test
    public void test02(){
//        String a = "111",b = "222";
//        a.equals(b);

//        DateTime dateTime = DateTime.now();
//        dateTime.toDateTime();
//        System.out.println(dateTime);

        Date data = new Date();
        data.getTime();
        System.out.println(data);
    }

}
