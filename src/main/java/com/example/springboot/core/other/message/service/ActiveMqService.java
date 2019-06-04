package com.example.springboot.core.other.message.service;


import com.example.springboot.core.relationaldb.mybatisredis.pojo.User;

public interface ActiveMqService {
    void sendMsg(String message);

    void receiveMsg(String message);

    void sendUser(User user);

    void receiveUser(User user);
}
