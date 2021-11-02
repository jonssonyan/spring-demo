package com.jonssonyan.controller;

import com.alibaba.fastjson.JSON;
import com.jonssonyan.entity.ConsumerTable;
import com.jonssonyan.entity.ProviderTable;
import com.jonssonyan.service.ConsumerService;
import com.jonssonyan.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class ConsumerController {
    @Reference(version = "1.0.0")
    private HelloService helloService;

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/sayHello")
    public List<ProviderTable> sayHello(@RequestParam(defaultValue = "hello", required = false) String name) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("==========>{} 开始请求接口，name参数为:{}", format.format(new Date()), name);
        String s = helloService.sayHello(name);
        List<ProviderTable> providerTables = JSON.parseArray(s, ProviderTable.class);
        log.info("==========>{} 调用common中HelloService接口,实现类是在provider工程中,返回的的内容是:{}", format.format(new Date()), providerTables.toString());
        log.info("==========>{} 开始将查询结果存入consumer的数据库", format.format(new Date()));
        ArrayList<ConsumerTable> list = new ArrayList<>();
        for (ProviderTable providerTable : providerTables) {
            ConsumerTable consumerTest = new ConsumerTable();
            consumerTest.setContent(providerTable.getContent());
            list.add(consumerTest);
        }
        consumerService.saveOrUpdateBatch(list);
        log.info("==========>{} 数据全部存储完毕", format.format(new Date()));
        return providerTables;
    }

    /**
     * 测试接口
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
