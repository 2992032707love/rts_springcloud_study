package com.rts.mq;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/31 18:54
 **/

@SpringBootTest
@Slf4j
public class RabbitMQTest {

    public static final String EXCHANGE_NAME = "exchange.tx.dragon";
    public static final String ROUTING_KEY = "routing.key.tx.dragon";

    public static final String QUEUE_NAME = "queue.tx.dragon";

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    @Transactional
    @Rollback(value = true)// junit 默认都是回滚事务，所以想提交事务就设置为false
    public void testSendMessageInTx() {
        // 1、发送第一条消息
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, "I am a dragon(tx msg ~~~01)");

        // 2、抛出异常
        log.info("do bad:" + 10 / 0);

        // 3、发送第二条消息
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, "I am a dragon(tx msg ~~~02)");
    }

}
