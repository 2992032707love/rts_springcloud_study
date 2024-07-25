package com.rts.mq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/25 18:24
 **/
@Component
@Slf4j
public class MyMessageListener {

    public static final String QUEUE_NAME = "queue.order";

    @RabbitListener(queues = {QUEUE_NAME})
    public void processMessage(String dataString, Message message, Channel channel) throws IOException {

        // 获取当前消息的 deliveryTag
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            // 核心操作 成功了 返回 ACK   失败  NACK
            log.info("消费端 消息内容： " + dataString);
            System.out.println(10/0);
            // 核心操作成功，返回 ACK 信息
            channel.basicAck(deliveryTag,false);
        } catch (Exception e) {

            // 获取当前消息是否是重新投递的
            // redelivered 为 true，即当前消息已经重复投递过一次了
            // redelivered 为 false，即当前消息是第一次投递
            Boolean redelivered = message.getMessageProperties().getRedelivered();

            // 核心操作失败，返回NACK信息
            // requeue 参数：控制消息是否重新放回队列
            //    true: 重新放回队列，broker 会重新投递这个消息
            //    false: 不重新放回队列， broker 会丢弃这个消息
            if (redelivered) {
                // 如果当前消息是重复投递的，说明此前已经重试过一次 所以 requeue 设置为 false，表示不重新放回队列
                channel.basicNack(deliveryTag,false,false);
            } else {
                // 如果当前消息是第一次投递的，说明当前第一次抛异常 所以 requeue 设置为 true，表示重新放回队列
                channel.basicNack(deliveryTag,false,true);
            }
            // reject 表示拒绝  辨析： basicNack() 和 basicReject() 方法区别
            // 前者可以控制是否批量操作，后者不行
            // channel.basicReject(deliveryTag,true);
            // throw new RuntimeException(e);
        }
    }
}
