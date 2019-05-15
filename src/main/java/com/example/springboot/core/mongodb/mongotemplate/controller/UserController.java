package com.example.springboot.core.mongodb.mongotemplate.controller;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import com.example.springboot.core.mongodb.mongotemplate.service.UserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    @ResponseBody
    public User saveUser(@RequestBody User user)
    {
        userService.saveUser(user);
        return user;
    }

    @RequestMapping("/get")
    @ResponseBody
    public User getUser(Long id)
    {
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping("/find")
    @ResponseBody
    public List<User> findUser(String userName, String note, Integer skip, Integer limit)
    {
        List<User> userList = userService.findUser(userName, note, skip, limit);
        return userList;
    }

    @RequestMapping("/update")
    @ResponseBody
    public UpdateResult updateUser(Long id, String userName, String note)
    {
        return userService.updateUser(id, userName, note);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DeleteResult deleteUser(Long id)
    {
        return userService.deleteUser(id);
    }

}
