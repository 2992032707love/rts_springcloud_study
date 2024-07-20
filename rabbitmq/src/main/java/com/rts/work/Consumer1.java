package com.rts.work;

import com.rabbitmq.client.*;
import com.rts.util.ConnectionUtil;

import java.io.IOException;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/18 21:51
 **/
public class Consumer1 {
    private static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer1 body: " + new String(body));
            }
        };

        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
