package com.example.springboot.core.mongodb.mongojpa.dao;

import com.example.springboot.core.mongodb.mongotemplate.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 使用自定义查询User，注意该类默认为XXXRepositoryImpl，不需要继承XXXRepository因此不需要重写各种方法，
     * Spring会自动加载该类为XXXRepository的实现类并且使用定义的方法
     *
     * @param id
     * @param userName
     * @return
     */
    public User findUserByIdOrName(Long id, String userName) {
        Criteria criteriaId = Criteria.where("id").is(id);
        Criteria criteriaName = Criteria.where("userName").is(userName);
        Criteria criteria = new Criteria().orOperator(criteriaId, criteriaName);
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query, User.class);
    }
}
