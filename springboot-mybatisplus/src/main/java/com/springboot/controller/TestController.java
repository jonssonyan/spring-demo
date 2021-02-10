package com.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.entity.Test;
import com.springboot.entity.vo.Result;
import com.springboot.entity.vo.TestVO;
import com.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/test")
    public Result<Object> selectPage(@RequestBody TestVO testVO) {
        IPage<Test> testIPage = testService.selectPage(testVO);
        return Result.success(testIPage);
    }
}
