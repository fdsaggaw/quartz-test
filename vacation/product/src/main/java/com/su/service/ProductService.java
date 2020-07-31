package com.su.service;

import com.github.pagehelper.PageInfo;
import com.su.entity.Product;

public interface ProductService {
    PageInfo<Product> findAllProduct(Integer pageNum, Integer pageSize);
}
