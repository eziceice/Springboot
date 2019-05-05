package com.example.springboot.core.relationaldb.jdbctemplate.service;

import com.example.springboot.core.relationaldb.jdbctemplate.pojo.UserJDBCTemplate;

public interface UserService {
    void printUser(UserJDBCTemplate userJDBCTemplate);
}
