package com.rts.mq;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/24 20:44
 **/
@SpringBootTest
public class RabbitMQTest {
    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String EXCHANGE_DIRECT_TIMEOUT = "exchange.direct.timeout";
    public static final String EXCHANGE_NORMAL = "exchange.normal.video";

    public static final String ROUTING_KEY_NORMAL = "routing.key.normal.video";
    public static final String ROUTING_KEY = "order";

    public static final String ROUTING_KEY_TIMEOUT = "routing.key.test.timeout";

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test01SendMessage(){
        String message = "你好 Message--- Test Confire---";
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT,ROUTING_KEY + "uiasd",message);
    }

    @Test
    public void test02SendMessage(){
        String message = "你好 Message--- Test prefetch--- 消息序号：";

        for (int i = 1; i <= 200; i++) {
            rabbitTemplate.convertAndSend(EXCHANGE_DIRECT,ROUTING_KEY ,message + i);
        }
    }

    /**
     * 队列层面的消息过期时间  单位毫秒
     */
    @Test
    public void test03SendMessage(){
        String message = "你好 Message--- Test timeout--- 消息序号：";

        for (int i = 1; i <= 100; i++) {
            rabbitTemplate.convertAndSend(EXCHANGE_DIRECT_TIMEOUT,ROUTING_KEY_TIMEOUT ,message + i);
        }
    }

    /**
     * 消息本身的过期时间设置  单位毫秒
     */
    @Test
    public void test04SendMessage(){
        String message = "你好 Message--- Test timeout--- 消息序号：";
        // 创建消息后置处理器对象
        MessagePostProcessor postProcessor = m -> {
            // 设置消息的过期时间 7000毫秒
            m.getMessageProperties().setExpiration("7000");
            return m;
        };
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT_TIMEOUT,ROUTING_KEY_TIMEOUT ,message + 1,postProcessor);
    }

    /**
     * 发送消息 死信消息产生原因一：拒绝
     */
    @Test
    public void testSendMessageButReject() {
        rabbitTemplate
                .convertAndSend(
                        EXCHANGE_NORMAL,
                        ROUTING_KEY_NORMAL,
                        "测试死信情况1：消息被拒绝");
    }

}
