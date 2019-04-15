package com.example.springboot.aop.service;

import com.example.springboot.aop.pojo.UserJDBCTemplate;
import org.springframework.stereotype.Service;

//@Service
public class UserServiceImpl implements UserService{

    @Override
    public void printUser(UserJDBCTemplate userJDBCTemplate){
        if (userJDBCTemplate == null)
        {
            throw new RuntimeException("UserJDBCTemplate is null!");
        }
        System.out.println("id = " + userJDBCTemplate.getId());
        System.out.println("username = " + userJDBCTemplate.getUserName());
        System.out.println("note = " + userJDBCTemplate.getNote());
    }
}
