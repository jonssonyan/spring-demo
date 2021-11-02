package com.jonssonyan.controller;

import com.jonssonyan.entity.User;
import com.jonssonyan.entity.vo.Result;
import com.jonssonyan.util.ValidatorUtil;
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
