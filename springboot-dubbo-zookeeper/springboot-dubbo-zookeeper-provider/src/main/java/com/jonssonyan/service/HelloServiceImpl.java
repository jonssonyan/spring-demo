package com.jonssonyan.service;

import com.alibaba.fastjson.JSON;
import com.jonssonyan.dao.ProviderDao;
import com.jonssonyan.entity.ProviderTable;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@Service(version = "1.0.0", interfaceClass = HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Autowired
    private ProviderDao providerDao;

    @Override
    public String sayHello(String name) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<ProviderTable> providerTables = providerDao.selectList(null);
        log.info("==========>{} provider查询数据库，查询结果为：{}", format.format(new Date()), providerTables);
        return JSON.toJSONString(providerTables);
    }
}