package com.java.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    @RequestMapping(value = {"/", "/index"})
    public String login() {
        return "login";
    }

    @RequestMapping("/user/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public ModelAndView toLogin(@RequestParam String username, @RequestParam String password, ModelAndView modelAndView) {
        // 获取当前用户subject
        Subject subject = SecurityUtils.getSubject();
        // 封装用户对象
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token); // 执行登陆操作
            modelAndView.setViewName("index");
            return modelAndView;
        } catch (UnknownAccountException u) { // 捕获用户名是否正确的异常
            modelAndView.addObject("msg", "用户名错误");
        } catch (IncorrectCredentialsException i) { // 密码是否正确的异常
            modelAndView.addObject("msg", "密码错误");
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/noAuth")
    @ResponseBody
    public String noAuth() {
        return "你尚未拥有权限";
    }
}