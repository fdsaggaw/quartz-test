package com.su.controller;

import com.github.pagehelper.PageInfo;
import com.su.entity.Product;
import com.su.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductHandler {
    @Autowired
    private ProductService productService;

    @GetMapping("/browse/{pageNumber}/{pageSize}")
    public PageInfo<Product> browse(@PathVariable("pageNumber") Integer pageNumber,
                         @PathVariable("pageSize") Integer pageSize){
        PageInfo<Product> pageProduct = productService.findAllProduct(pageNumber, pageSize);
        return pageProduct;
    }


}
