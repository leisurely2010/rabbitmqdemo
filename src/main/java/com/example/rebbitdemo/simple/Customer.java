package com.example.rebbitdemo.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * 消费者（老版本）
 * */
@SpringBootApplication
public class Customer {
    public static final String QUEUE_NAME="queuename01";
    public static void main(String[] args) throws Exception {

        //2、获取连接
        Connection connection = MyConnectionFactory.getConnection();
        //3、创建通道
        Channel channel = connection.createChannel();
        //4、创建消息队列
        /*
        队列名称，消息是否持久化，是否独占，是否自动删除,扩展参数
        * */
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //5、创建消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);
        //6、关联消息队列和消费者
        channel.basicConsume(QUEUE_NAME,true,consumer);
        //7、处理消息
        while (true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String msg=new String(delivery.getBody());
            System.out.println("消费者接受到消息："+msg);
        }

    }

}
