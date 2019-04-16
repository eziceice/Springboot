package com.example.springboot.aop;

import com.example.springboot.aop.config.RedisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

//@EnableJpaRepositories("com.example.springboot.aop.dao")
//@EntityScan("com.example.springboot.aop.pojo")
@MapperScan(basePackages = "com.example.springboot.aop.*",
sqlSessionFactoryRef = "sqlSessionFactory",
sqlSessionTemplateRef = "sqlSessionTemplate",
annotationClass = Repository.class)
@SpringBootApplication
public class SpringbootAopApplication {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init()
    {
        initRedisTemplate();
    }

    private void initRedisTemplate()
    {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAopApplication.class, args);
    }



}
