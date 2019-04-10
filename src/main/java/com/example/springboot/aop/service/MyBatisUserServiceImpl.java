package com.example.springboot.aop.service;

import com.example.springboot.aop.dao.MyBatisUserDao;
import com.example.springboot.aop.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyBatisUserServiceImpl implements MybatisUserService{

    @Autowired
    private MyBatisUserDao myBatisUserDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public User getUser(Long id) {
        return myBatisUserDao.getUser(id);
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1, propagation = Propagation.NESTED)
    public int insertUser(User user) {
        return myBatisUserDao.insertUser(user);
    }
}
