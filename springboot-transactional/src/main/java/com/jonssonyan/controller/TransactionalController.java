package com.jonssonyan.controller;

import com.jonssonyan.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionalController {
    @Autowired
    private TransactionalService transactionalService;


}
