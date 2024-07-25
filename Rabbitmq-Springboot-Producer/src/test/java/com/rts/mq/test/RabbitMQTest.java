package com.rts.mq.test;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/21 18:27
 **/
@SpringBootTest
public class RabbitMQTest {
    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String ROUTING_KEY = "order";
    public static final String QUEUE_NAME = "queue.order";

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void test01SednMessage(){
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT,ROUTING_KEY,"Hello xiyue");
    }
}
