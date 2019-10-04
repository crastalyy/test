package com.hw.shirospringboot.service;

import com.hw.shirospringboot.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User findByName(String name);
    public User findUserById(int id);
}
