package com.rts.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/7/18 21:22
 **/
public class ConnectionUtil {
    public static final String HOST_ADDRESS = "192.168.204.122";

    public static Connection getConnection() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost(HOST_ADDRESS);
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456789");

        Connection connection = connectionFactory.newConnection();
        return connection;
    }

    public static void main(String[] args) throws Exception {
        Connection con = ConnectionUtil.getConnection();
        // amqp://admin@192.168.204.122:5672/
        System.out.println(con);

        con.close();
    }
}
