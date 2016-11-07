package com.edu.nchu.distriuted.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * 消息发送者 (消息生产者)
 * Created by fujianjian on 2016/11/7.
 */
public class Sender {

    private final static String QUEUE_NAME = "HELLO";

    public static void main(String[] args) {

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setPort(5672);
            factory.setUsername("root");
            factory.setPassword("root");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "hello World";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("[x] sent' " + message + "' ");
//            channel.close();
//            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
