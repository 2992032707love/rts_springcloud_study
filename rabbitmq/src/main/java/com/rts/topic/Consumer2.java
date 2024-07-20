package com.rts.topic;

import com.rabbitmq.client.*;
import com.rts.util.ConnectionUtil;

import java.io.IOException;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/19 19:21
 **/
public class Consumer2 {
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        String queue2Name = "test_topic_queue2";
        channel.queueDeclare(queue2Name,true,false,false,null);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body: " + new String(body));
            }
        };
        channel.basicConsume(queue2Name,true,consumer);
    }
}
