package com.example.springboot.core.mongojpa.controller;

import com.example.springboot.core.mongojpa.dao.UserRepository;
import com.example.springboot.core.mongotemplate.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public List<User> findByUserName(String userName)
    {
        return userRepository.findByUserNameLike(userName);
    }
}
