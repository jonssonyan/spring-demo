package com.jonssonyan.dao;

import com.jonssonyan.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao extends ElasticsearchRepository<Message, String> {
    Page<Message> findAll(Pageable pageable);

    Iterable<Message> findAll(Sort sort);

    List<Message> findByContent(String content);

    Page<Message> findByContent(String content,Pageable pageable);

    List<Message> findByCreateTimeBetween(Long startTime, Long endTime);
}