package com.jonssonyan.listener;

import com.jonssonyan.entity.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class MyConsumer2 implements RocketMQListener<OrderPaidEvent> {

    @Override
    public void onMessage(OrderPaidEvent orderPaidEvent) {
        log.info("received orderPaidEvent: {}", orderPaidEvent);
    }
}
