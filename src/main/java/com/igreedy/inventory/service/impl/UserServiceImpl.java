package com.igreedy.inventory.service.impl;

import com.igreedy.inventory.mapper.UserMapper;
import com.igreedy.inventory.model.User;
import com.igreedy.inventory.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户Service实现类
 * Created by igreedy on 2018/7/22
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserInfo() {
        return userMapper.findUserInfo();
    }
}
