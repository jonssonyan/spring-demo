package com.springsecurity.controller;

import com.springsecurity.entity.User;
import com.springsecurity.entity.vo.ResultVO;
import com.springsecurity.service.UserService;
import com.springsecurity.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
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
        return ResultVOUtils.success();
    }

    /**
     * 该接口用于测试
     *
     * @return
     */
    @PostMapping("/user/selectOne")
    public ResultVO<Object> selectOne() {
        return ResultVOUtils.success();
    }

    /**
     * 该接口用于测试
     *
     * @return
     */
    @PostMapping("/user/admin/selectOne")
    public ResultVO<Object> selectOneAdmin() {
        return ResultVOUtils.success();
    }
}