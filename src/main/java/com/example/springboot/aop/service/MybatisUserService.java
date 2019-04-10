package com.example.springboot.aop.service;

import com.example.springboot.aop.pojo.User;

public interface MybatisUserService {
    User getUser(Long id);
    int insertUser(User user);
}
