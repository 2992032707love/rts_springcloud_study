package com.rts.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/8/5 21:50
 **/
@Slf4j
@Configuration
public class MQProducerAckConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 消息发送到交换机成功或失败时调用这个方法
        if (ack) {
            log.info("confirm() 回调函数：" + "消息发送成功：" + "correlationData: " + correlationData);
            log.info("confirm() 回调函数：" + "消息发送成功：" + "ack: " + ack);
        } else {
            log.info("confirm() 回调函数：" + "消息发送失败：" + "correlationData: " + correlationData);
            log.info("confirm() 回调函数：" + "消息发送失败：" + "ack: " + ack);
            log.info("confirm() 回调函数：" + "消息发送失败：" + "cause: " + cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        // 发送到队列失败时才调用这个方法
        log.info("returnedMessage() 回调函数 " + "消息主体: " + new String(returned.getMessage().getBody()));
        log.info("returnedMessage() 回调函数 " + "应答码: " + returned.getReplyCode());
        log.info("returnedMessage() 回调函数 " + "描述：" + returned.getReplyText());
        log.info("returnedMessage() 回调函数 " + "消息使用的交换器 exchange : " + returned.getExchange());
        log.info("returnedMessage() 回调函数 " + "消息使用的路由键 routing : " + returned.getRoutingKey());
    }
}
