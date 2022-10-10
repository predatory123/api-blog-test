package com.spring.blog.vo.params;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author: wyj
 * @date: 2022/1/19 18:05
 */
@Data
public class UpdateParams {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long customerId;
}
