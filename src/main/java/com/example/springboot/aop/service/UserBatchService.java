package com.example.springboot.aop.service;

import com.example.springboot.aop.pojo.User;

import java.util.List;

public interface UserBatchService {
    int insertUsers(List<User> userList);
}
