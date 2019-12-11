package com.example.rebbitdemo.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Producter {
    public static final String QUEUE_NAME="queuename01";
    public static void main(String[] args) throws Exception {
        //2、获取连接
        Connection connection = MyConnectionFactory.getConnection();
        //3、创建通道
        Channel channel = connection.createChannel();
        //4、创建消息队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //5、处理消息
        /*交换机名称，路由键（queuename），额外参数，消息
        * */
        String msg="hello rabbitmq!";
        for(int i=0;i<500;i++){
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        }
        //5、关闭
        channel.close();
        connection.close();
    }

}
