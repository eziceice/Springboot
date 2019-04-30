package com.example.springboot.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.Duration;

//@EnableJpaRepositories("com.example.springboot.core.jpa.dao.*")
//@EntityScan("com.example.springboot.aop.pojo")
@MapperScan(basePackages = "com.example.springboot.core.mybatisredis.dao.*",
sqlSessionFactoryRef = "sqlSessionFactory",
sqlSessionTemplateRef = "sqlSessionTemplate",
annotationClass = Repository.class)
@SpringBootApplication
@EnableCaching
public class CoreApplication {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private MessageListener redisMessageListener;

    private ThreadPoolTaskScheduler taskScheduler;

    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler()
    {
        if (taskScheduler != null)
            return taskScheduler;
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    @Bean
    public RedisMessageListenerContainer initRedisContainer()
    {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.setTaskExecutor(initTaskScheduler());
        Topic topic = new ChannelTopic("topic1");
        container.addMessageListener(redisMessageListener, topic);
        return container;
    }

    @Bean(name = "redisCacheManager")
    public RedisCacheManager initRedisCacheManager()
    {
        // Redis加锁的写入器
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory);
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
        config = config.disableKeyPrefix();
        config = config.entryTtl(Duration.ofMinutes(10));
        RedisCacheManager redisCacheManager = new RedisCacheManager(writer, config);
        return redisCacheManager;
    }


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
        SpringApplication.run(CoreApplication.class, args);
    }
}
