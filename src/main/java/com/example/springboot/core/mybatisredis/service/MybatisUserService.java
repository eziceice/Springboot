package com.example.springboot.core.mybatisredis.service;

import com.example.springboot.core.mybatisredis.pojo.User;

import java.util.List;

public interface MybatisUserService {
    User getUser(Long id);
    int insertUser(User user);
    User updateUserName(Long id, String userName);
    List<User> findUsers(String userName, String note);
    int deleteUser(Long id);
}
