package com.spring.blog.vo.params;

import lombok.Data;

/**
 * 定义分页属性
 * @author wyj
 * @date 2021/8/9 14:43
 */
@Data
public class PageParams {

    private int page = 1;

    private int pageSize = 10;

    private Long categoryId;

    private Long tagId;

    private String year;

    private String month;

    public String getMonth(){
        if (this.month != null && this.month.length() == 1){
            return "0" + this.month;
        }
        return this.month;
    }
}
