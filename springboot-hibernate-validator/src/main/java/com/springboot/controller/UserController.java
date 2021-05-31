package com.springboot.controller;

import com.springboot.entity.User;
import com.springboot.entity.vo.Result;
import com.springboot.util.ValidatorUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/insertUser")
    public Result insertUser(@RequestBody User user) {
        ValidatorUtil.validateEntity(user);
        return Result.success();
    }
}
