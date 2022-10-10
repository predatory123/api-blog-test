package com.spring.blog.controller;

import com.spring.blog.service.CommentService;
import com.spring.blog.vo.Result;
import com.spring.blog.vo.params.CommentParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wyj
 * @date 2021/8/23 15:18
 */
@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("article/{id}")
    public Result comments(@PathVariable("id") Long id){
        return commentService.commentsByArticleId(id);
    }

    @PostMapping("create/change")
    public Result comment(@RequestBody CommentParams commentParams){
        return commentService.comment(commentParams);
    }
}
