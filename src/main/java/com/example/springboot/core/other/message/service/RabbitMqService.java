package com.example.springboot.core.other.message.service;


import com.example.springboot.core.mongodb.mongotemplate.pojo.User;

public interface RabbitMqService {
    void sendUser(User user);
}
