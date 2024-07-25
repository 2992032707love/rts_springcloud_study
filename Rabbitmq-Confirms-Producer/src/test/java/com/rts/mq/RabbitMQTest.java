package com.rts.mq;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/24 20:44
 **/
@SpringBootTest
public class RabbitMQTest {
    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String ROUTING_KEY = "order";

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test01SendMessage(){
        String message = "你好 Message--- Test Confire---";
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT,ROUTING_KEY + "uiasd",message);
    }
}
