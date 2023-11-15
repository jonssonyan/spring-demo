package com.jonssonyan.service.impl;

import com.jonssonyan.dao.Table1Dao;
import com.jonssonyan.dao.Table2Dao;
import com.jonssonyan.entity.Table1;
import com.jonssonyan.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private Table1Dao table1Dao;
    @Autowired
    private Table2Dao table2Dao;

    /**
     * 声明式事务
     */
    @Override
    @Transactional
    public void transactionalTest() {
        Table1 table1 = new Table1();
//        table1Dao.saveTable1();
    }
}
