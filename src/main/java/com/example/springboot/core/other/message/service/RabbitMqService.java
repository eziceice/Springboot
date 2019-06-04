package com.example.springboot.core.other.message.service;

import com.example.springboot.core.relationaldb.mybatisredis.pojo.User;

public interface RabbitMqService {
    void sendMsg(String msg);

    void sendUser(User user);
}
