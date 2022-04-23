package com.jonssonyan.controller;

import com.jonssonyan.entity.User;
import com.jonssonyan.pojo.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/selectList")
    public Result selectList() {
        List<User> list = new ArrayList<>();
        list.add(new User("杰斯", "123456", "147258369"));
        list.add(new User("马克", "4567899", "151369851"));
        list.add(new User("雷克", "789456", "151753852"));
        list.add(new User("詹姆斯", "112233", "13252364521"));
        list.add(new User("斯蒂芬", "223344", "19265321856"));
        list.add(new User("雷克顿", "556677", "15213652154"));
        return Result.success(list);
    }
}
