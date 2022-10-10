package com.spring.blog.controller;

import com.spring.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author wyj
 * @date 2021/8/23 19:18
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping
    public Result upload(@RequestParam("image")MultipartFile file){
        // 原始文件名称，比如“xxx.png
        String originalFilename = file.getOriginalFilename();
        // 生成随机的文件名称，唯一
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename,".");



        return Result.success(file);
    }
}
