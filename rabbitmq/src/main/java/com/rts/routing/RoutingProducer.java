package com.rts.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rts.util.ConnectionUtil;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/19 17:14
 **/
public class RoutingProducer {
    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_direct_routing";

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT,true,false,false,null);

        String queue1Name = "test_direct_queue1";

        String queue2Name = "test_direct_queue2";

        // 声明(创建)队列
        // queue            参数1: 队列名称
        // durable          参数2: 是否定义持久化队列，当MQ重启之后还在
        // exclusive        参数3: 是否独占本次连接。若独占，只能有一个消费者监听这个队列且Connection关闭时删除这个队列
        // autoDelete       参数4: 是否在不使用的时候自动删除队列，也就是在没有Consumer时自动删除
        // grguments        参数5: 队列其它参数
        channel.queueDeclare(queue1Name,true,false,false,null);
        channel.queueDeclare(queue2Name,true,false,false,null);

        // 队列绑定交换机
        // 队列1绑定 error
        // 参数1.queue:队列名称
        // 参数2.exchange:交换机名称
        // 参数3.routingKey:路由键，绑定规则
        // 如果交换机的类型为fanout，routingKey设置为""
        channel.queueBind(queue1Name,exchangeName,"error");

        // 队列2绑定info error warning
        channel.queueBind(queue2Name,exchangeName,"error");
        channel.queueBind(queue2Name,exchangeName,"info");
        channel.queueBind(queue2Name,exchangeName,"warning");

//        String message = "日志信息，旭旭宝宝调用了delete方法，错误了，日志级别 warning";
        String message = "日志信息，旭旭宝宝调用了delete方法，错误了，日志级别 error";
//        String message = "日志信息，旭旭宝宝调用了delete方法，错误了，日志级别 info";
        // 发送消息
        channel.basicPublish(exchangeName,"error",null,message.getBytes());
        System.out.println(message);

        // 释放资源
        channel.close();
        connection.close();
    }
}
