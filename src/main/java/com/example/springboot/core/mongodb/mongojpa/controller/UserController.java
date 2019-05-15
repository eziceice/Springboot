package com.example.springboot.core.mongodb.mongojpa.controller;

import com.example.springboot.core.mongodb.mongojpa.dao.UserRepository;
import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/byName")
    @ResponseBody
    public User findByUserName(String userName)
    {
        return userRepository.findByUserName(userName);
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public User saveUser(@RequestBody User user)
    {
        return userRepository.save(user);
    }
}
