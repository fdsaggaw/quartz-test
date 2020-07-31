package com.su.service;

import com.su.entity.Account;

public interface AccountService {
    /**
     * 查找用户
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    Account findAccount(String username, String password);

}
