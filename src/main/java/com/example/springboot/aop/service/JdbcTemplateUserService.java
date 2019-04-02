package com.example.springboot.aop.service;

import com.example.springboot.aop.pojo.User;

import java.util.List;

public interface JdbcTemplateUserService {
    User getUser(Long id);

    List<User> findUsers(String userName, String note);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Long id);
}
