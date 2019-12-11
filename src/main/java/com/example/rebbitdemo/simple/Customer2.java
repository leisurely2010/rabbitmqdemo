package com.example.rebbitdemo.simple;

import com.rabbitmq.client.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/*
* 消费者（新版本）
* */
@SpringBootApplication
public class Customer2 {
    public static final String QUEUE_NAME = "queuename01";

    public static void main(String[] args) throws Exception {

        //2、获取连接
        Connection connection = MyConnectionFactory.getConnection();
        //3、创建通道
        Channel channel = connection.createChannel();
        //4、创建消息队列
        /*
        队列名称，消息是否持久化，是否独占，是否自动删除,扩展参数
        * */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("接收到的消息：" + msg);
            }

        };
        //6、关联消息队列和消费者
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }

}
