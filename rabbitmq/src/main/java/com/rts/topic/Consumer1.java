package com.rts.topic;

import com.rabbitmq.client.*;
import com.rts.util.ConnectionUtil;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/19 19:18
 **/
public class Consumer1 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        String queue1Name = "test_topic_queue1";
        channel.queueDeclare(queue1Name,true,false,false,null);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body: " + new String(body));
            }
        };
        channel.basicConsume(queue1Name,true,consumer);

    }
}
