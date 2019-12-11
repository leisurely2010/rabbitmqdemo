package com.example.rebbitdemo.simple;

import com.rabbitmq.client.*;

public class MyConnectionFactory {

    public static Connection getConnection ()throws Exception {
        //1、获取工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.0.140");
        factory.setPort(5672);
        factory.setVirtualHost("/helloworld");
        factory.setUsername("lei");
        factory.setPassword("lei");
        //2、获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
