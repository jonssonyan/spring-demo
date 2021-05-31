package com.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api("汽车接口")
@RestController
public class CarController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "查询汽车列表")
    @GetMapping(value = "/select/{param1}/{param2}")
    public String carPage(@ApiParam("参数1") @PathVariable("param1") String param1, @ApiParam("参数2") @PathVariable("param2") String param2) {
        return String.format("参数1：%s，参数2：%s", param1, param2);
    }
}