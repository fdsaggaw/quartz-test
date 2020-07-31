package com.su.feign;

import com.su.entity.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "cart")
public interface CartFeign {
    @GetMapping("/cart/addToCart/{productId}/{userId}")
    public void addToCart(@PathVariable("productId") Long cartId, @PathVariable("userId") Long userId);

    @GetMapping("/cart/findAll/{userId}")
    public List<Cart> findAll(@PathVariable("userId") Long userId);

    @RequestMapping(value = "/cart/deleteProduct/{cartId}")
    public void deleteProduct(@PathVariable("cartId") Long cartId);

    /**
     * 减少一件商品
     * @param cartId
     * @return
     */
    @RequestMapping(value = "/cart/deleteOneProduct/{cartId}")
    public Map<String,Object> deleteOneProduct(@PathVariable("cartId") Long cartId);

    /**
     * 增加一件商品
     * @param cartId
     * @return
     */
    @RequestMapping(value = "/cart/addOneProduct/{cartId}")
    public Map<String,Object> addOneProduct(@PathVariable("cartId") Long cartId);
}
