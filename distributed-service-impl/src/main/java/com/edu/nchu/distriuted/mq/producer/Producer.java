package com.edu.nchu.distriuted.mq.producer;

import com.edu.nchu.distriuted.mq.model.EndPoint;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者
 * Created by fujianjian on 2016/11/13.
 */
public class Producer extends EndPoint{

    public Producer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}