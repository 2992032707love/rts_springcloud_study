package com.rts.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/20 22:51
 **/
@Component
@Slf4j
public class MyMessageListener {
    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String ROUTING_KEY = "order";
    public static final String QUEUE_NAME = "queue.order";

//    // 写法一： 监听 + 在 Rabbitmq 服务器上创建交换机、队列
//    @RabbitListener(bindings = @QueueBinding(
//            // 指定队列的信息
//            value = @Queue(
//                    // 队列名称
//                    value = QUEUE_NAME,
//                    // 持久化
//                    durable = "true"),
//            // 指定交换机的信息
//            exchange = @Exchange(
//                    // 交换机名称
//                    value = EXCHANGE_DIRECT),
//            // 指定路由键信息  字符串数组
//            key = {ROUTING_KEY}
//    ))
    //  写法二： 监听
    @RabbitListener(queues = QUEUE_NAME)
    public void processMessage(String dateString, // 消息本身
                               Message message,   // 消息对象  如果没有用专门的一个参数接收消息，那么直接用这个
                               Channel channel) { // 频道对象
        System.out.println("消费端接收到了消息： " + dateString);
    }

}
