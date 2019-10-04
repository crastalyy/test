package com.hw.shirospringboot.mapper;

import com.hw.shirospringboot.entity.User;

public interface UserMapper {
    User findUserByName(String name);
    User findUserById(int id);
}