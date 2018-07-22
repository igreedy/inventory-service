package com.igreedy.inventory.mapper;

import com.igreedy.inventory.model.User;

/**
 * 测试用户的Mapper接口说
 * Created by igreedy on 2018/7/22
 *
 */
public interface UserMapper {

    /**
     * 查询测试用户信息
     * @return 测试用户信息
     */
    public User findUserInfo();

}
