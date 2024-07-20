package com.rts.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/18 20:09
 **/
public class Producer {
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
        channel.queueDeclare("simple_queue",true,false,false,null);

        // 要发送的信息
        String message = "你好，小兔子1234567！";
        // 参数1:交换机名称,如果没有指定则使用默认Default Exchange
        // 参数2:路由key,简单模式可以传递队列名称
        // 参数3:配置信息
        // 参数4:消息内容
        channel.basicPublish("","simple_queue",null,message.getBytes());

        System.out.println("已发送消息" + message);
        // 关闭资源
        channel.close();
        connection.close();
    }
}