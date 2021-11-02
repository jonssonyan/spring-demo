package com.jonssonyan.listener;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 从RocketMQ Spring 2.2.0开始，RocketMQ Spring支持Pull模式消费
 * 对于慢消费，消息量有限且到来的速度不均匀的情况，pull模式比较合适
 */
@Component
public class RocketMQListener {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void pullConsumer() {
        //使用 rocketMQTemplate pull consumer
        List<String> messages = rocketMQTemplate.receive(String.class);
        System.out.printf("receive from rocketMQTemplate, messages=%s %n", messages);
    }
}