package com.su.feign;

import com.su.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "account")
public interface AccountFeign {
    @GetMapping("/account/login/{username}/{password}")
    public Account login(@PathVariable("username") String username,@PathVariable("password") String password);
}
