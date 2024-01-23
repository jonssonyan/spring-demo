package com.jonssonyan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jonssonyan.entity.Test;
import com.jonssonyan.entity.vo.Result;
import com.jonssonyan.entity.vo.TestVO;
import com.jonssonyan.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/test")
    public Result selectPage(@RequestBody TestVO testVO) {
        IPage<Test> testIPage = testService.selectPage(testVO);
        return Result.success(testIPage);
    }
}
