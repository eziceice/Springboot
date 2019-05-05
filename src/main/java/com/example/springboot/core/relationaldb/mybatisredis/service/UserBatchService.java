package com.example.springboot.core.relationaldb.mybatisredis.service;

import com.example.springboot.core.relationaldb.mybatisredis.pojo.User;

import java.util.List;

public interface UserBatchService {
    int insertUsers(List<User> userList);
}
