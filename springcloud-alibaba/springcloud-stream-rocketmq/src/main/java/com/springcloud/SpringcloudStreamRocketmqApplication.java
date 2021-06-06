package com.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@EnableBinding(SpringcloudStreamRocketmqApplication.PolledProcessor.class)
public class SpringcloudStreamRocketmqApplication {

    private final Logger logger =
            LoggerFactory.getLogger(SpringcloudStreamRocketmqApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudStreamRocketmqApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(PollableMessageSource source,
                                    MessageChannel dest) {
        return args -> {
            while (true) {
                boolean result = source.poll(m -> {
                    String payload = (String) m.getPayload();
                    logger.info("Received: " + payload);
                    dest.send(MessageBuilder.withPayload(payload.toUpperCase())
                            .copyHeaders(m.getHeaders())
                            .build());
                }, new ParameterizedTypeReference<String>() {
                });
                if (result) {
                    logger.info("Processed a message");
                } else {
                    logger.info("Nothing to do");
                }
                Thread.sleep(5_000);
            }
        };
    }

    public static interface PolledProcessor {

        @Input
        PollableMessageSource source();

        @Output
        MessageChannel dest();

    }
}
