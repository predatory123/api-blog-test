package com.spring.blog.controller;

import com.spring.blog.dao.pojo.ProductModel;
import com.spring.blog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author wyj
 * @date 2021/8/24 10:04
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    public String list(Model model){
        List<ProductModel> productModels = productService.list();
        model.addAttribute("productList", productModels);
        return "list";
    }

    @ResponseBody
    @RequestMapping("index")
    public String index() {
        return "index.jsp";
    }

    @RequestMapping("addProduct")
    public String fileUpload(MultipartFile file, ProductModel productModel) throws IOException {
        /**
         * 上传图片
         */
        //图片上传成功后，将图片的地址写到数据库
        //保存图片的路径（这是存在我项目中的images下了，你们可以设置路径）
        String filePath = "D:\\workspace\\java_test\\dc-auto\\blog-api\\src\\main\\resources\\static\\img";
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        //新的文件名字
        String newFileName = UUID.randomUUID() + originalFilename;
        //封装上传文件位置的全路径
        File targetFile = new File(filePath, newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        productModel.setImage(newFileName);
        /**
         * 保存商品
         */
        productService.save(productModel);
        return "redirect:/product/list";
    }
}
