package com.edu.nchu.distriuted.mq;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消息接收者 (消息消费者)
 * Created by fujianjian on 2016/11/7.
 */
public class Receiver {

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
            System.out.println("[*] Waiting for messages. To exit press CTRL + C");
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("[x] Received' "+ message + "'");
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);
//            channel.close();
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
