package com.springboot.controller;

import com.springboot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/selectList")
    public String index(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("杰斯", "123456", "147258369"));
        list.add(new User("马克", "4567899", "951369851"));
        list.add(new User("雷克", "789456", "951753852"));
        model.addAttribute("accountList", list);
        return "user";
    }
}
