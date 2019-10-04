package com.hw.shirospringboot.service.impl;

import com.hw.shirospringboot.entity.User;
import com.hw.shirospringboot.mapper.UserMapper;
import com.hw.shirospringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class
UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }
}
