package com.spring.blog.vo.params;

import lombok.Data;

/**
 * @author wyj
 * @date 2021/8/23 15:51
 */
@Data
public class CommentParams {

    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;
}
