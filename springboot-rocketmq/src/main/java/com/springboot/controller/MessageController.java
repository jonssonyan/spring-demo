package com.springboot.controller;

import com.springboot.entity.OrderPaidEvent;
import com.springboot.entity.vo.Result;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class MessageController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @PostMapping("/sendMessage")
    public Result sendMessage() {
        // 同步发送消息
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        // 发送spring消息
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        // 异步发送消息
        rocketMQTemplate.asyncSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }
        });
        // 有序的发送消息
        rocketMQTemplate.syncSendOrderly("orderly_topic", MessageBuilder.withPayload("Hello, World").build(), "hashkey");
        //  一旦rocketMQTemplate被销毁，您就不能用这个rocketMQTemplate再次发送任何消息
        //rocketMQTemplate.destroy();
        return Result.success();
    }
}
