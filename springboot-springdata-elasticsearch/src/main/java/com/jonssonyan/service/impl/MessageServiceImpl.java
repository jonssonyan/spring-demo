package com.jonssonyan.service.impl;

import com.jonssonyan.dao.MessageDao;
import com.jonssonyan.entity.Message;
import com.jonssonyan.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public Page<Message> findAll(Pageable pageable) {
        return messageDao.findAll(pageable);
    }

    @Override
    public Iterable<Message> findAll(Sort sort) {
        return messageDao.findAll(sort);
    }

    @Override
    public List<Message> findByContent(String content) {
        return messageDao.findByContent(content);
    }

    @Override
    public List<Message> findByCreateTimeBetween(Long startTime, Long endTime) {
        return messageDao.findByCreateTimeBetween(startTime, endTime);
    }

    @Override
    public void save(Message message) {
        messageDao.save(message);
    }

    @Override
    public void deleteById(String id) {
        messageDao.deleteById(id);
    }

    @Override
    public Page<Message> findByContent(String content, Pageable pageable) {
        return messageDao.findByContent(content, pageable);
    }
}
