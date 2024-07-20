package com.rts.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rts.util.ConnectionUtil;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/19 19:00
 **/
public class TopicProducer {
    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_topic";
        // 参数1.exchange:交换机名称
        // 参数2.type:交换机类型
        //   DIRECT("direct"):定向
        //   FANOUT("fanout"):扇形(广播)，发送消息到每一个与之绑定队
        //   TOPIC("topic”):通配符的方式
        //   HEADERS("headers"):参数匹配
        // 参数3.durable:是否持久化
        // 参数4.autoDelete:自动删除
        // 参数5.internal:内部使用。一般false
        // 参数6.arguments:其它参数
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true,false,false,null);

        String queue1Name = "test_topic_queue1";
        String queue2Name = "test_topic_queue2";

        channel.queueDeclare(queue1Name,true,false,false,null);
        channel.queueDeclare(queue2Name,true,false,false,null);

        // 绑定队列和交换机
        // 参数1.queue:队列名称
        // 参数2.exchange:交换机名称
        // 参数3.routingKey:路由键,绑定规则
        //      如果交换机的类型为fanout，routingKey设置为""
        // routing key 常用格式:系统的名称.日志的级别。
        // 需求:所有error级别的日志存入数据库,所有order系统的日志存入数据库
        channel.queueBind(queue1Name,exchangeName,"#.error");
        channel.queueBind(queue1Name,exchangeName,"order.*");
        channel.queueBind(queue2Name,exchangeName,"*.*");

        // 分别发送消息到队列: order.info、goods.info、goods.error
//        String body = "[所在系统: order][日志级别: info][日志内容: 订单生成: 保存成功]";
//        channel.basicPublish(exchangeName,"order.info",null,body.getBytes());

//        String body = "[所在系统: goods][日志级别: info][日志内容: 商品发布成功]";
//        channel.basicPublish(exchangeName,"goods.info",null,body.getBytes());

        String body = "[所在系统: goods][日志级别: error][日志内容: 商品发布失败]";
        channel.basicPublish(exchangeName,"goods.error",null,body.getBytes());

        channel.close();
        connection.close();
    }
}
