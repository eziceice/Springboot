package com.example.springboot.core.other.message.controller;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import com.example.springboot.core.other.message.service.RabbitMqService;
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

    @GetMapping("/user")
    public Map<String, Object> user(Long id, String userName)
    {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
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
