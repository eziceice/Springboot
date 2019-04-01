package com.example.springboot.aop.service;

import com.example.springboot.aop.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public void printUser(User user){
        if (user == null)
        {
            throw new RuntimeException("User is null!");
        }
        System.out.println("id = " + user.getId());
        System.out.println("username = " + user.getUserName());
        System.out.println("note = " + user.getNote());
    }
}
