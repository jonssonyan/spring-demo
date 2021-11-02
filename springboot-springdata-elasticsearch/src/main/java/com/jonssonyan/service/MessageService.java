package com.jonssonyan.service;

import com.jonssonyan.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface MessageService {
    Page<Message> findAll(Pageable pageable);

    Iterable<Message> findAll(Sort sort);

    List<Message> findByContent(String content);

    List<Message> findByCreateTimeBetween(Long startTime, Long endTime);

    void save(Message message);

    void deleteById(String id);

    Page<Message> findByContent(String content, Pageable pageable);
}