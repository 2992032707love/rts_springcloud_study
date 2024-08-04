package com.rts.mq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/25 18:24
 **/
@Component
@Slf4j
public class MyMessageListener {

    public static final String QUEUE_NAME = "queue.order";

    public static final String QUEUE_NAME_TIMEOUT = "queue.test.timeout";

    public static final String EXCHANGE_NORMAL = "exchange.normal.video";
    public static final String EXCHANGE_DEAD_LETTER = "exchange.dead.letter.video";

    public static final String ROUTING_KEY_NORMAL = "routing.key.normal.video";
    public static final String ROUTING_KEY_DEAD_LETTER = "routing.key.dead.letter.video";

    public static final String QUEUE_NORMAL = "queue.normal.video";
    public static final String QUEUE_DEAD_LETTER = "queue.dead.letter.video";
    public static final String QUEUE_DELAY = "queue.test.delay";
    public static final String QUEUE_PRIORITY = "queue.test.priority";

//    @RabbitListener(queues = {QUEUE_NAME})
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

//    @RabbitListener(queues = {QUEUE_NAME})
    public void processMessagetest(String dataString, Message message, Channel channel) throws IOException {

        // 获取当前消息的 deliveryTag
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            // 核心操作 成功了 返回 ACK   失败  NACK
            log.info("消费端 消息内容： " + dataString);
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
//    @RabbitListener(queues = {QUEUE_NAME})
    public void processMessagePrefetch(String dataString, Message message, Channel channel) throws IOException {

        // 获取当前消息的 deliveryTag
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            // 核心操作 成功了 返回 ACK   失败  NACK
            log.info("消费端 消息内容： " + dataString);
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

//    /**
//     * 接收消息 监听正常队列 死信消息产生原因一：拒绝
//     * @param message
//     * @param channel
//     * @throws IOException
//     */
//    @RabbitListener(queues = {QUEUE_NORMAL})
//    public void processMessageNormal(Message message, Channel channel) throws IOException {
//        // 监听正常队列，但是拒绝消息
//        log.info("★[normal]消息接收到，但我拒绝。");
//        channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//    }

    /**
     * 监听死信队列
     * @param dataString
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = {QUEUE_DEAD_LETTER})
    public void processMessageDead(String dataString, Message message, Channel channel) throws IOException {
        // 监听死信队列
        log.info("★[dead letter]dataString = " + dataString);
        log.info("★[dead letter]我是死信监听方法，我接收到了死信消息");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 接收消息 监听正常队列 死信消息产生原因二：消息数量超过队列的最大容量
     * 消息接收代码不再拒绝消息
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = {QUEUE_NORMAL})
    public void processMessageNormal(Message message, Channel channel) throws IOException {
        // 监听正常队列
        log.info("★[normal]消息接收到。");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 未创建队列和交换机
     */
//    public static final String EXCHANGE_DELAY = "exchange.test.delay";
//    public static final String ROUTING_KEY_DELAY = "routing.key.test.delay";
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = QUEUE_DELAY, durable = "true", autoDelete = "false"),
//            exchange = @Exchange(
//                    value = EXCHANGE_DELAY,
//                    durable = "true",
//                    autoDelete = "false",
//                    type = "x-delayed-message",
//                    arguments = @Argument(name = "x-delayed-type", value = "direct")),
//            key = {ROUTING_KEY_DELAY}
//    ))
    /**
     * 延迟队列 消费者端 已创建队列和交换机
     * @param dataString
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = {QUEUE_DELAY})
    public void processMessageDelay(String dataString, Message message, Channel channel) throws IOException{
        log.info("[生产者][消息本身]" + dataString);
        log.info("[消费者][当前时间]" + new SimpleDateFormat("hh:mm:ss").format(new Date()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = {QUEUE_PRIORITY})
    public void processMessagePriority(String dataString, Message message, Channel channel) throws IOException {
        log.info("[生产者][消息本身]" + dataString);
        log.info("[消费者][当前时间]" + new SimpleDateFormat("hh:mm:ss").format(new Date()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
