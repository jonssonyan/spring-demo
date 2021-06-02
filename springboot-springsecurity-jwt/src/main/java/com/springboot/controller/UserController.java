package com.springboot.controller;

import com.springboot.entity.User;
import com.springboot.entity.vo.ResultVO;
import com.springboot.service.UserService;
import com.springboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 注册用户,不需要登录
     *
     * @param user
     * @return
     */
    @PutMapping("/register")
    public ResultVO<Object> insert(@RequestBody User user) {
        // 密码加密存入到数据库
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.insert(user);
        return Result.success();
    }

    /**
     * 该接口用于测试
     *
     * @return
     */
    @PostMapping("/user/selectOne")
    public ResultVO<Object> selectOne() {
        return Result.success();
    }

    /**
     * 该接口用于测试
     *
     * @return
     */
    @PostMapping("/user/admin/selectOne")
    public ResultVO<Object> selectOneAdmin() {
        return Result.success();
    }
}