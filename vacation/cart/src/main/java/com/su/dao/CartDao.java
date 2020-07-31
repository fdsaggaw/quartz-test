package com.su.dao;

import com.su.entity.Cart;

import java.util.List;

public interface CartDao {
    /**
     * 添加商品
     * @param cart
     */
    void insertProduct(Cart cart);

    /**
     * 根据商品id和用户id查找商品
     * @param productId
     * @return
     */
    Cart findProductByProductIdAndUserId(Long productId,Long userId);


    /**
     * 修改购物车里商品数量
     * @param cart
     */
    void updateQuantityAndAmount(Cart cart);

    /**
     * 查询购物车所有内容
     * @return
     */
    List<Cart> findAll(Long userId);

    /**
     * 根据购物车id删除商品
     * @param cartId
     */
    void deleteProductById(Long cartId);

    /**
     * 根据id查找商品
     * @param cartId
     * @return
     */
    Cart findProductById(Long cartId);

}
