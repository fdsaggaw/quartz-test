package com.su.service.impl;

import com.su.dao.AccountDao;
import com.su.entity.Account;
import com.su.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public Account findAccount(String username, String password) {
        return accountDao.findAccountByUsernameAndPassword(username,password);
    }
}
