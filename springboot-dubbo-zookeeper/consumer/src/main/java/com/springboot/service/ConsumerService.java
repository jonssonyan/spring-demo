package com.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dao.ConsumerDao;
import com.springboot.entity.ConsumerTable;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService extends ServiceImpl<ConsumerDao, ConsumerTable> {
}
