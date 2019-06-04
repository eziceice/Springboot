package com.example.springboot.core.other.message.controller;

import com.example.springboot.core.other.message.service.RabbitMqService;
import com.example.springboot.core.relationaldb.mybatisredis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/rabbitmq")
public class RabbitMqController {

    @Autowired
    private RabbitMqService rabbitMqService;

    @GetMapping("/msg")
    public Map<String, Object> msg(String message)
    {
        rabbitMqService.sendMsg(message);
        return result("message", message);
    }

    @GetMapping("/user")
    public Map<String, Object> user(Long id, String userName, String note)
    {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);
        rabbitMqService.sendUser(user);
        return result("user", user);
    }

    private Map<String, Object> result(String key, Object message) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put(key, message);
        return result;
    }
}
