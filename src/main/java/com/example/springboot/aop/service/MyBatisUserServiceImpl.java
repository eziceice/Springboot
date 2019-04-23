package com.example.springboot.aop.service;

import com.example.springboot.aop.dao.MyBatisUserDao;
import com.example.springboot.aop.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyBatisUserServiceImpl implements MybatisUserService{

    @Autowired
    private MyBatisUserDao myBatisUserDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    @Cacheable(value = "redisCache", key = "'redis_user_'+#id")
    public User getUser(Long id) {
        return myBatisUserDao.getUser(id);
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1, propagation = Propagation.NESTED)
    @CachePut(value = "redisCache", key = "'redis_user_'+#result.id")
    public int insertUser(User user) {
        return myBatisUserDao.insertUser(user);
    }

    @Override
    @Transactional
    @CachePut(value = "redisCache", condition = "#result != 'null'", key = "'redis_user_'+#id")
    public User updateUserName(Long id, String userName) {
        User user = this.getUser(id);
        if (user == null)
        {
            return null;
        }
        user.setUserName(userName);
        myBatisUserDao.updateUser(user);
        return user;
    }

    @Override
    @Transactional
    public List<User> findUsers(String userName, String note) {
        return myBatisUserDao.findUser(userName, note);
    }

    @Override
    @Transactional
    @CacheEvict(value = "redisCache", key = "'redis_user_'+#id", beforeInvocation = false)
    public int deleteUser(Long id) {
        return myBatisUserDao.deleteUser(id);
    }
}
