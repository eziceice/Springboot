package com.example.springboot.core.service;

import com.example.springboot.core.pojo.User;

import java.util.List;

public interface UserBatchService {
    int insertUsers(List<User> userList);
}
