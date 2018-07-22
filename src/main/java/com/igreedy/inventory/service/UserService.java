package com.igreedy.inventory.service;

import com.igreedy.inventory.model.User;

/**
 * 用户Service接口
 * Created by igreedy on 2018/7/22
 */
public interface UserService {

    /**
     * 查询用户信息
     * @return 用户信息
     */
    public User findUserInfo();

}
