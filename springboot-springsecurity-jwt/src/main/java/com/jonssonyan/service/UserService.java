package com.jonssonyan.service;

import com.jonssonyan.entity.User;

public interface UserService {
    User selectByUsername(String username);

    void insert(User user);
}
