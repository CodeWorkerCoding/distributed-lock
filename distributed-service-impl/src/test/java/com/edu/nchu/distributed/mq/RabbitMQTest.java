package com.edu.nchu.distributed.mq;

import com.edu.nchu.distriuted.mq.Consumer.QueueConsumer;
import com.edu.nchu.distriuted.mq.producer.Producer;
import org.junit.Test;

import java.util.HashMap;

/**
 * RabbitMq 模型测试
 * Created by fujianjian on 2016/11/13.
 */
public class RabbitMQTest {

    @Test
    public void testRabbitMq() throws  Exception {
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
        QueueConsumer consumer1 = new QueueConsumer("queue");
        Thread consumerThread1 = new Thread(consumer1);
        consumerThread1.start();

        Producer producer = new Producer("queue");

        for (int i = 0; i < 100; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }
    }

}
