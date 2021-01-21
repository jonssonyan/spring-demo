package com.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springsecurity.dao.RoleDao;
import com.springsecurity.dao.UserDao;
import com.springsecurity.dao.UserRoleDao;
import com.springsecurity.entity.Role;
import com.springsecurity.entity.User;
import com.springsecurity.entity.UserRole;
import com.springsecurity.exception.UserNameNotFoundException;
import com.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public User selectByUsername(String username) {
        User user = userDao.selectByUsername(username);
        if (user == null) {
            // 抛出未查到用户名的异常
            HashMap<String, Object> map = new HashMap<>();
            map.put("username:", username);
            throw new UserNameNotFoundException(map);
        }
        return user;
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
        // 判断角色表中是否有普通用户的角色
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "USER");
        Role role = roleDao.selectOne(queryWrapper);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        if (role != null) {
            userRole.setRoleId(role.getId());
        } else {
            Role roleUser = new Role();
            roleUser.setName("USER");
            int insert = roleDao.insert(roleUser);
            userRole.setRoleId((long) insert);
        }
        userRoleDao.insert(userRole);
    }
}