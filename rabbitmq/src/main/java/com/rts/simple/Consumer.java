package com.rts.simple;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/18 20:10
 **/
public class Consumer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.204.122");
        connectionFactory.setPort(5672);
        // 虚拟主机名称
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456789");
        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 创建频道
        Channel channel = connection.createChannel();

        // 声明(创建)队列
        // queue            参数1: 队列名称
        // durable          参数2: 是否定义持久化队列，当MQ重启之后还在
        // exclusive        参数3: 是否独占本次连接。若独占，只能有一个消费者监听这个队列且Connection关闭时删除这个队列
        // autoDelete       参数4: 是否在不使用的时候自动删除队列，也就是在没有Consumer时自动删除
        // grguments        参数5: 队列其它参数
        // channel.queueDeclare("simple_queue",true,false,false,null);

        // 接收消息
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            // 回调方法，当收到消息后，会自动执行该方法
            // 参数1.consumerTag:标识
            // 参数2.envelope:获取一些信息，交换机，路由key...
            // 参数3.properties:配置信息
            // 参数4.body:数据
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag: " + consumerTag);
                System.out.println("Exchange: " + envelope.getExchange());
                System.out.println("RoutingKey: " + envelope.getRoutingKey());
                System.out.println("properties: " + properties);
                System.out.println("body: " + new String(body));
            }
        };

        // 参数1.queue:队列名称
        // 参数2.autoAck:是否自动确认，类似咱们发短信，发送成功会收到一个确认消息
        // 参数3.callback:回调对象
        // 消费者类似一个监听程序，主要是用来监听消息
        channel.basicConsume("simple_queue",true,consumer);

    }
}
