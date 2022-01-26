package com.jonssonyan.controller;

import com.jonssonyan.entity.RedisKey;
import com.jonssonyan.entity.User;
import com.jonssonyan.entity.vo.ResultVO;
import com.jonssonyan.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/redis")
public class Controller {
    @Autowired
    private RedisService redisService;

    @PostMapping("/setValue")
    public ResultVO<Object> set() {
        User user = new User();
        user.setId(1L);
        user.setName("wang");
        user.setAge(22);
        redisService.setValue(RedisKey.builder().prefix("user").suffix("wang").build(), user, 10000L);
        return ResultVO.success(redisService.getValue(RedisKey.builder().prefix("user").suffix("wang").build(), user.getClass()));
    }

    @PostMapping("/setFile")
    public ResultVO<Object> setFile() {
        RedisService.Files files = redisService.new Files();
        try {
            files.setFile(ResourceUtils.getFile("classpath:static/测试.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ResultVO.success(files.getFile("测试"));
    }
}