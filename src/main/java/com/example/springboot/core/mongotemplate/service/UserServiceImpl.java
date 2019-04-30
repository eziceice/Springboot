package com.example.springboot.core.mongotemplate.service;

import com.example.springboot.core.mongotemplate.pojo.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user, "user");
    }

    @Override
    public DeleteResult deleteUser(Long id) {
        Criteria criteriaId = Criteria.where("id").is(id);
        Query queryId = Query.query(criteriaId);
        DeleteResult result = mongoTemplate.remove(queryId, User.class);
        return result;
    }

    @Override
    public List<User> findUser(String userName, String note, int skip, int limit) {
        Criteria criteria = Criteria.where("userName").regex(userName).and("note").regex(note);
        // Skip前skip个，并且最多返回limit个
        Query query = Query.query(criteria).limit(limit).skip(skip);
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

    @Override
    public UpdateResult updateUser(Long id, String userName, String note) {
        Criteria criteriaId = Criteria.where("id").is(id);
        Query query = Query.query(criteriaId);

        Update update = Update.update("userName", userName);
        update.set("note", note);
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        // Update Multiple
        // UpdateResult result = mongoTemplate.updateMulti(query, update, User.class);
        return result;
    }

    @Override
    public User getUser(Long id) {
        return mongoTemplate.findById(id, User.class);
        /*
        如果只需要获取第一个，也可以采用如下查询方法
        Criteria criteriaId = Criteria.where("id").is(id);
        Query queryId = Query.query(criteriaId);
        return mongoTemplate.findOne(queryId, User.class);
         */
    }
}
