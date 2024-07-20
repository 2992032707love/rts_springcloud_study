package com.rts.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rts.util.ConnectionUtil;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/18 21:34
 **/
public class Producer {

    private static final String QUEUE_NAME = "work_queue";
    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        for (int i = 0; i < 10; i++) {
            String body = i + "hello rabbitmq----";

            channel.basicPublish("",QUEUE_NAME,null,body.getBytes());
        }

        channel.close();
        connection.close();
    }
}
