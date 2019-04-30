package com.example.springboot.core.service;

import com.example.springboot.core.pojo.UserJDBCTemplate;

import java.util.List;

public interface JdbcTemplateUserService {
    UserJDBCTemplate getUser(Long id);

    List<UserJDBCTemplate> findUsers(String userName, String note);

    int insertUser(UserJDBCTemplate userJDBCTemplate);

    int updateUser(UserJDBCTemplate userJDBCTemplate);

    int deleteUser(Long id);
}
