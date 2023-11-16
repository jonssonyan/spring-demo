package com.jonssonyan;

import com.jonssonyan.service.TransactionalService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class SpringbootTransactionalApplicationTests {

    @Autowired
    private TransactionalService transactionalService;

    @Test
    void contextLoads() {
    }

    @Test
    void transactionalTest() {
        transactionalService.transactionalTest();
    }
}
