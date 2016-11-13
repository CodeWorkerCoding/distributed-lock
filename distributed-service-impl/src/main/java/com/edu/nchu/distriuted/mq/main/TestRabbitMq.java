package com.edu.nchu.distriuted.mq.main;

import com.edu.nchu.distriuted.mq.Consumer.QueueConsumer;
import com.edu.nchu.distriuted.mq.producer.Producer;

import java.util.HashMap;

/**
 * Created by Alen on 2016/11/13.
 */
public class TestRabbitMq {

    public TestRabbitMq() throws  Exception {
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        QueueConsumer consumer1 = new QueueConsumer("queue");
        Thread consumerThread1 = new Thread(consumer1);
        consumerThread1.start();

        Producer producer = new Producer("queue");

        for (int i = 0; i < 1000; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }
    }

    public static void main(String[] args) throws Exception {
        new TestRabbitMq();
    }
}
