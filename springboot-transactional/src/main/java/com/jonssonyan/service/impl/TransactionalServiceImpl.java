package com.jonssonyan.service.impl;

import com.jonssonyan.dao.Table1Dao;
import com.jonssonyan.dao.Table2Dao;
import com.jonssonyan.entity.Table1;
import com.jonssonyan.entity.Table2;
import com.jonssonyan.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalServiceImpl implements TransactionalService {
    @Autowired
    private Table1Dao table1Dao;
    @Autowired
    private Table2Dao table2Dao;

    /**
     * 声明式事务
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void transactionalTest() {
        Table1 table1 = new Table1();
        table1.setName("name1");
        table1Dao.insert(table1);
        Table2 table2 = new Table2();
        table1.setName("name2");
        table2Dao.insert(table2);
        // 抛出异常回滚
        throw new RuntimeException("custom exception");
    }
}
