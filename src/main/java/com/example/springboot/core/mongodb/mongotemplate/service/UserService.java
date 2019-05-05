package com.example.springboot.core.mongodb.mongotemplate.service;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    DeleteResult deleteUser(Long id);

    List<User> findUser(String userName, String note, int skip, int limit);

    UpdateResult updateUser(Long id, String userName, String note);

    User getUser(Long id);
}
