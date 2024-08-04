package com.rts.mq;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/24 20:44
 **/
@SpringBootTest
public class RabbitMQTest {
    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String EXCHANGE_DIRECT_TIMEOUT = "exchange.direct.timeout";
    public static final String EXCHANGE_NORMAL = "exchange.normal.video";
    public static final String EXCHANGE_DELAY = "exchange.test.delay";
    public static final String EXCHANGE_PRIORITY = "exchange.test.priority";
    public static final String ROUTING_KEY_PRIORITY = "routing.key.test.priority";
    public static final String ROUTING_KEY_NORMAL = "routing.key.normal.video";
    public static final String ROUTING_KEY = "order";

    public static final String ROUTING_KEY_TIMEOUT = "routing.key.test.timeout";
    public static final String ROUTING_KEY_DELAY = "routing.key.test.delay";

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

    /**
     * 发送消息 死信消息产生原因二：消息数量超过队列的最大容量
     */
    @Test
    public void testSendMultiMessage() {
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend(
                    EXCHANGE_NORMAL,
                    ROUTING_KEY_NORMAL,
                    "测试死信情况2：消息数量超过队列的最大容量" + i);
        }
    }

    /**
     * 发送消息 死信消息产生原因三：消息超时未消费
     */
    @Test
    public void testSendMessageTimeout() {
        rabbitTemplate
                .convertAndSend(
                        EXCHANGE_NORMAL,
                        ROUTING_KEY_NORMAL,
                        "测试死信情况3：消息超时");
    }

    /**
     * 发送消息 延迟队列
     */
    @Test
    public void testSendMessageDelay() {
        // 创建消息后置处理器
        MessagePostProcessor postProcessor = message -> {
            // 设置消息过期时间(以毫秒为单位)
            // x-delay 参数必须基于 x-delayed-message-exchange 插件才能生效
            message.getMessageProperties().setHeader("x-delay","10000");
            System.out.println(message.getMessageProperties().getReceivedDeliveryMode());
            System.out.println(message.getMessageProperties().toString());
            System.out.println("设置延迟时间为10s");

            return message;
        };
        System.out.println(postProcessor.toString());
        // 发送消息
        rabbitTemplate.convertAndSend(
                EXCHANGE_DELAY,
                ROUTING_KEY_DELAY,
                "test delay message by plugin " + new SimpleDateFormat("HH:mm:ss").format(new Date()),
                postProcessor
        );
    }

    /**
     * 发送消息 优先级队列  优先级数字越大优先级越高 切记：不能超过 x-max-priority的值这里设置为10
     */
    @Test
    public void testSendMessagePriority() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_PRIORITY,
                ROUTING_KEY_PRIORITY,
                "I am a message with priority 3.",
                message -> {
                    // 设置消息优先级
                    // 数字越大优先级越高 切记：不能超过 x-max-priority的值这里设置为10
                    message.getMessageProperties().setPriority(3);
                    return message;});
    }
}
