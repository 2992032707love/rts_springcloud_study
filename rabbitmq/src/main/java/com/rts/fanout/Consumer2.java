package com.rts.fanout;

import com.rabbitmq.client.*;
import com.rts.util.ConnectionUtil;

import java.io.IOException;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/19 16:55
 **/
public class Consumer2 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        String queueNameTwo = "fanout_queue_two_test";
        channel.queueDeclare(queueNameTwo,true,false,false,null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body: " + new String(body));
                System.out.println("队列 2 消费者 2 将日志信息打印到控制台......");
            }
        };
        channel.basicConsume(queueNameTwo,true,consumer);
    }
}
