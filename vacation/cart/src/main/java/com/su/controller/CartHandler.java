package com.su.controller;

import com.su.entity.Cart;
import com.su.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartHandler {
    @Autowired
    private CartService cartService;

    @GetMapping("/addToCart/{productId}/{userId}")
    public void addToCart(@PathVariable("productId") Long productId, @PathVariable("userId") Long userId){
        cartService.addToCart(productId,userId);
    }

    /**
     * 查询购物车列表
     * @param
     * @return
     */
    @GetMapping("/findAll/{userId}")
    public List<Cart> findAll(@PathVariable("userId") Long userId){
        List<Cart> cartList = cartService.findAll(userId);
        return cartList;
    }

    @RequestMapping(value = "/deleteProduct/{cartId}")
    public void deleteProduct(@PathVariable("cartId") Long cartId){
        cartService.deleteProduct(cartId);
    }

    /**
     * 减少一件商品
     * @param cartId
     * @return
     */
    @RequestMapping(value = "/deleteOneProduct/{cartId}")
    public Map<String,Object> deleteOneProduct(@PathVariable("cartId") Long cartId){
        Map<String, Object> map = new HashMap<>(16);
        if(cartService.deleteOneProduct(cartId)){
            Integer quantity = cartService.findProductById(cartId).getQuantity();
            BigDecimal amount = cartService.findProductById(cartId).getAmount();
            map.put("flag",true);
            map.put("quantity",quantity);
            map.put("amount",amount);
            return map;
        }else{
            map.put("flag",false);
            return map;
        }
    }

    /**
     * 增加一件商品
     * @param cartId
     * @return
     */
    @RequestMapping(value = "/addOneProduct/{cartId}")
    public Map<String,Object> addOneProduct(@PathVariable("cartId") Long cartId){
        Map<String, Object> map = new HashMap<>(16);
        if(cartService.addOneProduct(cartId)){
            map.put("flag",true);
            map.put("quantity",cartService.findProductById(cartId).getQuantity());
            map.put("amount",cartService.findProductById(cartId).getAmount());
            return map;
        }else {
            map.put("flag",false);
            return map;
        }
    }
}
