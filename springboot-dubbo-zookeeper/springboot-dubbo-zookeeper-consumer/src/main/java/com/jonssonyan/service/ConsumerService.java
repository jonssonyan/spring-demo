package com.jonssonyan.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jonssonyan.dao.ConsumerDao;
import com.jonssonyan.entity.ConsumerTable;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService extends ServiceImpl<ConsumerDao, ConsumerTable> {
}
