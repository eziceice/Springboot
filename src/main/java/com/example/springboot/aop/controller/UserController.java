package com.example.springboot.aop.controller;

import com.example.springboot.aop.pojo.User;
import com.example.springboot.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(Long id, String userName, String note)
    {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);
        userService.printUser(user);
        return user;
    }
}
