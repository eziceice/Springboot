package com.example.springboot.aop.service;

import com.example.springboot.aop.pojo.UserJDBCTemplate;

public interface UserService {
    void printUser(UserJDBCTemplate userJDBCTemplate);
}
