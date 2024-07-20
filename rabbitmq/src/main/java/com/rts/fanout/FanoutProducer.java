package com.rts.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rts.util.ConnectionUtil;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/18 22:40
 **/
public class FanoutProducer {

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

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
        String exchangeName = "fanout_exchangeName";
        // 创建交换机
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT,true,false,false,null);
        // 创建队列
        String queueNameOne = "fanout_queue_one_test";
        String queueNameTwo = "fanout_queue_two_test";

        channel.queueDeclare(queueNameOne,true,false,false,null);
        channel.queueDeclare(queueNameTwo,true,false,false,null);

        // 5、绑定队列和交换机
        // 参数1.queue:队列名称
        // 参数2.exchange:交换机名称
        // 参数3.routingKey:路由键，绑定规则
        // 如果交换机的类型为fanout，routingKey设置为""
        channel.queueBind(queueNameOne,exchangeName,"");
        channel.queueBind(queueNameTwo,exchangeName,"");

        String body = "日志信息，rts调用了findAll方法...日志级别：debug...";

        // 发送消息
        channel.basicPublish(exchangeName,"",null,body.getBytes());

        channel.close();
        connection.close();

    }
}
