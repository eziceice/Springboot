package com.example.springboot.core.mongojpa.dao;

import com.example.springboot.core.mongotemplate.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    List<User> findByUserNameLike(String userName);

    @Query("{'id': ?0, 'userName': ?1}")
    User find(Long id, String userName);

    User findUserByIdOrUserName(Long id, String userName);
}
