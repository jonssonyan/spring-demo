package com.springsecurity.service;

import com.springsecurity.entity.User;

public interface UserService {
    User selectByUsername(String username);

    void insert(User user);
}
