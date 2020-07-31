package com.su.controller;

import com.su.dao.AccountDao;
import com.su.entity.Account;
import com.su.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountHandler {
    @Autowired
    private AccountService accountService;

    @GetMapping("/login/{username}/{password}")
    public Account login(@PathVariable("username") String username,@PathVariable("password") String password ){
        return accountService.findAccount(username,password);
    }
}
