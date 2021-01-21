package com.springboot.service;

import com.springboot.entity.User;

public interface UserService {
    User selectByUsername(String username);

    void insert(User user);
}
