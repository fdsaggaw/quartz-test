package com.su.feign;

import com.github.pagehelper.PageInfo;
import com.su.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "product")
public interface ProductFeign {
    @GetMapping("/product/browse/{pageNumber}/{pageSize}")
    public PageInfo<Product> browse(@PathVariable("pageNumber") Integer pageNumber,
                                    @PathVariable("pageSize") Integer pageSize);


}
