package com.example.springboot.core.relationaldb.jdbctemplate.service;

import com.example.springboot.core.relationaldb.jdbctemplate.pojo.UserJDBCTemplate;

import java.util.List;

public interface JdbcTemplateUserService {
    UserJDBCTemplate getUser(Long id);

    List<UserJDBCTemplate> findUsers(String userName, String note);

    int insertUser(UserJDBCTemplate userJDBCTemplate);

    int updateUser(UserJDBCTemplate userJDBCTemplate);

    int deleteUser(Long id);
}
