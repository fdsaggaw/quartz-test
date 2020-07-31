package com.su.service.impl;

import com.su.dao.CartDao;
import com.su.dao.ProductDao;
import com.su.entity.Cart;
import com.su.entity.Product;
import com.su.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;
    @Override
    public void addToCart(Long productId, Long userId) {
        Cart productExisted = cartDao.findProductByProductIdAndUserId(productId,userId);
        Product productById = productDao.findProductById(productId);
        BigDecimal price = productById.getPrice();
        if ( null== productExisted){
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(1);
            cart.setAmount(price);
            cartDao.insertProduct(cart);
        }else {
            productExisted.setQuantity(productExisted.getQuantity()+1);
            BigDecimal amount = new BigDecimal(String.valueOf(productExisted.getQuantity())).multiply(price);
            productExisted.setAmount(amount);
            cartDao.updateQuantityAndAmount(productExisted);
        }
    }

    @Override
    public List<Cart> findAll(Long userId) {
        List<Cart> cartList = cartDao.findAll(userId);
        List<Cart> removedList = new ArrayList<>();
        for (Cart cart:cartList) {
            Product product = productDao.findProductById(cart.getProductId());
            //TODO 判空
            if (product==null){
                cartDao.deleteProductById(cart.getId());
                removedList.add(cart);
            }else {
                cart.setProductName(product.getName());
            }
        }
        cartList.removeAll(removedList);
        return cartList;
    }

    @Override
    public void deleteProduct(Long cartId) {
        cartDao.deleteProductById(cartId);
    }

    @Override
    public Boolean addOneProduct(Long cartId) {
        Cart cart = cartDao.findProductById(cartId);
        Product product = productDao.findProductById(cart.getProductId());
        if (cart.getQuantity()<product.getStock()){
            cart.setQuantity(cart.getQuantity()+1);
            cart.setAmount(cart.getAmount().add(product.getPrice()));
            cartDao.updateQuantityAndAmount(cart);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteOneProduct(Long cartId) {
        Cart cart = cartDao.findProductById(cartId);
        if (cart.getQuantity()==1){
            return false;
        }else{
            cart.setQuantity(cart.getQuantity()-1);
            cart.setAmount(cart.getAmount().subtract(productDao.findProductById(cart.getProductId()).getPrice()));
            cartDao.updateQuantityAndAmount(cart);
            return true;
        }
    }

    @Override
    public Cart findProductById(Long cartId) {
        return cartDao.findProductById(cartId);
    }
}
