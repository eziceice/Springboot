package com.example.springboot.aop.service;

import com.example.springboot.aop.dao.MyBatisUserDao;
import com.example.springboot.aop.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBatisUserServiceImpl implements MybatisUserService{

    @Autowired
    private MyBatisUserDao myBatisUserDao;

    @Override
    public User getUser(Long id) {
        return myBatisUserDao.getUser(id);
    }
}
