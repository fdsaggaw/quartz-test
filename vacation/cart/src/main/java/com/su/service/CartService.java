package com.su.service;

import com.su.entity.Cart;

import java.util.List;

public interface CartService {
    /**
     * 添加商品到购物车
     * @param productId
     * @param userId
     */
    void addToCart(Long productId,Long userId);

    /**
     * 查询购物车全部内容
     * @return
     */
    List<Cart> findAll(Long userId);

    /**
     * 删除商品
     * @param cartId
     */
    void deleteProduct(Long cartId);

    /**
     * 添加一件商品
     * @param cartId
     */
    Boolean addOneProduct(Long cartId);

    /**
     * 删除一件商品
     * @param cartId
     */
    Boolean deleteOneProduct(Long cartId);

    Cart findProductById(Long cartId);


}
