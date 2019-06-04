package com.example.springboot.core.other.message.service;

import com.example.springboot.core.relationaldb.mybatisredis.pojo.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqServiceImpl implements RabbitMqService, RabbitTemplate.ConfirmCallback {

    @Value("${rabbitmq.queue.msg}")
    private String msgRouting;

    @Value("${rabbitmq.queue.user}")
    private String userRouting;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg(String msg) {
        System.out.println("Send Message: " + msg);
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.convertAndSend(msg);
    }

    @Override
    public void sendUser(User user) {
        System.out.println("Send User: " + user);
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.convertAndSend(user);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("Message has been consumed successfully");
        } else {
            System.out.println("Message consumed failed " + s);
        }
    }
}
