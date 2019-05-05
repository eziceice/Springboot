package com.example.springboot.core.relationaldb.mybatisredis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        String body = new String(message.getBody());

        String topic = new String(bytes);
        System.out.println(body);
        System.out.println(topic);
    }
}
