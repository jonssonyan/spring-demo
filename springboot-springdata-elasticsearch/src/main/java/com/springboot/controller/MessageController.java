package com.springboot.controller;

import com.springboot.entity.Message;
import com.springboot.entity.vo.MessageVO;
import com.springboot.entity.vo.ResultVO;
import com.springboot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/selectPage")
    public ResultVO<Object> selectPage(@RequestBody MessageVO messageVO) {
        return ResultVO.success(messageService.findAll(PageRequest.of(messageVO.getPageNum(), messageVO.getPageSize())));
    }

    @PostMapping("/selectSort")
    public ResultVO<Object> selectSort(@RequestBody MessageVO messageVO) {
        return ResultVO.success(messageService.findAll(Sort.by("createTime").descending()));
    }

    @PostMapping("/findByContent")
    public ResultVO<Object> findByContent(@RequestBody MessageVO messageVO) {
        return ResultVO.success(messageService.findByContent(messageVO.getContent()));
    }

    @PostMapping("/findByContentPage")
    public ResultVO<Object> findByContentPage(@RequestBody MessageVO messageVO) {
        return ResultVO.success(messageService.findByContent(messageVO.getContent(), PageRequest.of(messageVO.getPageNum(), messageVO.getPageSize())));
    }

    @PostMapping("/findByCreateTimeBetween")
    public ResultVO<Object> findByCreateTimeBetween(@RequestBody MessageVO messageVO) {
        return ResultVO.success(messageService.findByCreateTimeBetween(messageVO.getStartTime(), messageVO.getEndTime()));
    }

    @PostMapping("/insert")
    public ResultVO<Object> insert(@RequestBody Message message) {
        messageService.save(message);
        return ResultVO.success();
    }

    @PostMapping("/delete/{id}")
    public ResultVO<Object> delete(@PathVariable String id) {
        messageService.deleteById(id);
        return ResultVO.success();
    }

    @PostMapping("/test")
    public ResultVO<Object> test() {
        return ResultVO.success();
    }
}