package com.rts.mq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/8/6 0:09
 **/
@Component
@Slf4j
public class MyProcessor {
    @RabbitListener(queues = {"queue.cluster.test"})
    public void processNormalQueueMessage(String data, Message message, Channel channel)
            throws IOException {

        log.info("消费端：" + data);

        // 确认消息已被消费
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
