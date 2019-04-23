package com.example.springboot.aop.service;

import com.example.springboot.aop.pojo.User;

import java.util.List;

public interface MybatisUserService {
    User getUser(Long id);
    int insertUser(User user);
    User updateUserName(Long id, String userName);
    List<User> findUsers(String userName, String note);
    int deleteUser(Long id);
}
