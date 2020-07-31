package com.su.controller;

import com.github.pagehelper.PageInfo;
import com.su.entity.Account;
import com.su.entity.Cart;
import com.su.entity.Product;
import com.su.feign.AccountFeign;
import com.su.feign.CartFeign;
import com.su.feign.ProductFeign;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/client")
@SessionAttributes(value = {"account","carts"})
public class ClientHandler {
    @Autowired
    private AccountFeign accountFeign;

    @Autowired
    private ProductFeign productFeign;

    @Autowired
    private CartFeign cartFeign;

    @RequestMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/login")
    public String login(String username, String password, Model model){
        Account account = accountFeign.login(username, password);
        if (null == account){
            model.addAttribute("msg","用户名或密码错误");
            return "redirect:/client/loginPage";
        }
        model.addAttribute("account",account);
        return "redirect:/client/browse";
    }

    @GetMapping("/browse")
    public String browse(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize,Model model){
        PageInfo<Product> pageProduct = productFeign.browse(pageNum, pageSize);
        System.out.println(pageProduct);
        if (pageProduct.getTotal()==0){
            model.addAttribute("message","维护中");
        }else {
            model.addAttribute("pageProduct",pageProduct);
        }
        return "list";
    }

    /**
     * 添加至购物车
     * @param
     * @return
     */
    @RequestMapping("/addToCart")
    @ResponseBody
    public Map<String,Object> addToCart(Long productId, ModelMap modelMap){
        Account account = (Account)modelMap.get("account");
        System.out.println("-----------------------");
        Long userId = account.getId();
        Map<String, Object> map = new HashMap<>(16);
        cartFeign.addToCart(productId,userId);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        map.put("flag",true);
        return map;
    }

    /**
     * 查询购物车列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/findAll")
    public String findAll(Model model,ModelMap modelMap){
        Account account = (Account)modelMap.get("account");
        List<Cart> cartList = cartFeign.findAll(account.getId());
        model.addAttribute("carts",cartList);
        return "cart";
    }

    @RequestMapping(value = "/deleteProduct")
    public String deleteProduct(@RequestParam Long cartId){
        cartFeign.deleteProduct(cartId);
        return "redirect:/client/findAll";
    }

    /**
     * 减少一件商品
     * @param cartId
     * @return
     */
    @RequestMapping(value = "/deleteOneProduct")
    @ResponseBody
    public Map<String,Object> deleteOneProject(@RequestParam Long cartId){
        Map<String, Object> deleteMap = cartFeign.deleteOneProduct(cartId);
        return deleteMap;
    }

    /**
     * 增加一件商品
     * @param cartId
     * @return
     */
    @RequestMapping(value = "/addOneProduct")
    @ResponseBody
    public Map<String,Object> addOneProject(@RequestParam Long cartId){
        Map<String, Object> addMap = cartFeign.addOneProduct(cartId);
        return addMap;
    }
}
