package com.example.springboot.aop.dao;

import com.example.springboot.aop.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBatisUserDao {
    User getUser(Long id);
}
