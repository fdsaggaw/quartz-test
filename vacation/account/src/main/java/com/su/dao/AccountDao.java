package com.su.dao;

import com.su.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author suwliang
 * @Title UserDao
 * @Description
 * @date 2019/12/31
 */

public interface AccountDao {
    /**
     * 根据用户名和密码查找用户
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    Account findAccountByUsernameAndPassword(String username, String password);

    /**
     * 查询用户名
     * @return
     */
    List<String> findUsername();

    void insertUser(Account account);


}
