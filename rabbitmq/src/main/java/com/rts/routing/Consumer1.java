package com.rts.routing;

import com.rabbitmq.client.*;
import com.rts.util.ConnectionUtil;

import java.io.IOException;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/19 17:26
 **/
public class Consumer1 {

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        String queue1Name = "test_direct_queue1";

        channel.queueDeclare(queue1Name,true,false,false,null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body: " + new String(body));
                System.out.println("Consumer1 将日志信息打印到控制台......");
            }
        };
        // 参数1.queue:队列名称
        // 参数2.autoAck:是否自动确认，类似咱们发短信，发送成功会收到一个确认消息
        // 参数3.callback:回调对象
        // 消费者类似一个监听程序，主要是用来监听消息
        channel.basicConsume(queue1Name,true,consumer);
    }
}
