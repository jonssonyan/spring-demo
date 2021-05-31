package com.springboot.controller;

import com.springboot.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    // @GetMapping("send/{message}")
    // public void send(@PathVariable String message) {
    // this.kafkaTemplate.send("test", message);
    // ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("test", message);
    // future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
    //     @Override
    //     public void onSuccess(SendResult<String, String> result) {
    //         logger.info("成功发送消息：{}，offset=[{}]", message, result.getRecordMetadata().offset());
    //     }
    //
    //     @Override
    //     public void onFailure(Throwable ex) {
    //         logger.error("消息：{} 发送失败，原因：{}", message, ex.getMessage());
    //     }
    // });
    // }

    @GetMapping("send/{message}")
    public void sendMessage(@PathVariable String message) {
        this.kafkaTemplate.send("test", new Message("demo", message));
    }
}
