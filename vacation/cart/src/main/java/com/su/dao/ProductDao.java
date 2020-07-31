package com.su.dao;

import com.su.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ProductDao {
    /**
     * 根据id查找该商品
     * @param productId
     * @return 商品
     */
    Product findProductById(Long productId);


}
