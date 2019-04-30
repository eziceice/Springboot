package com.example.springboot.core.mybatisredis.service;

import com.example.springboot.core.mybatisredis.pojo.User;

import java.util.List;

public interface UserBatchService {
    int insertUsers(List<User> userList);
}
