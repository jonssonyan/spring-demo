package com.jonssonyan.controller;

import com.jonssonyan.entity.RedisKey;
import com.jonssonyan.entity.User;
import com.jonssonyan.entity.vo.ResultVO;
import com.jonssonyan.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private RedisService redisService;

    @PostMapping("/redis/set")
    public ResultVO<Object> set() {
        User user = new User();
        user.setId(1L);
        user.setName("wang");
        user.setAge(22);
        redisService.setValue(RedisKey.builder().prefix("user").suffix("wang").build(), user, 10000L);
        return ResultVO.success(redisService.getValue(RedisKey.builder().prefix("user").suffix("wang").build(), user.getClass()));
    }
}