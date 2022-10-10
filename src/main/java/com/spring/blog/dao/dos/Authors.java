package com.spring.blog.dao.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wyj
 * @date 2021/8/12 14:15
 */
@Data
public class Authors {

    private String nickName;

    private Integer count;
}
