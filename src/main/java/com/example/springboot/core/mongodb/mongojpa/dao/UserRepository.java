package com.example.springboot.core.mongodb.mongojpa.dao;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    User findByUserName(String userName);

    @Override
    <S extends User> S save(S s);
}
