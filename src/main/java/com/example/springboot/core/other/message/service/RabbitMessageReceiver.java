package com.example.springboot.core.other.message.service;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageReceiver {

    @RabbitListener(queues = {"${rabbitmq.queue.msg}"})
    public void receiveMsg(String msg)
    {
        System.out.println("Received message: " + msg);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.user}"})
    public void receiveUser(User user)
    {
        System.out.println("Received user: " + user);
    }
}
