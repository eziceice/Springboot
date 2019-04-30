package com.example.springboot.core.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

//@Configuration

/**
 * How to config Redis Manually in Springboot
 */
public class RedisConfig {

    private RedisConnectionFactory connectionFactory;

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFacotry()
    {
        if (this.connectionFactory != null)
            return connectionFactory;
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(30);//最大空闲数
        poolConfig.setMaxTotal(50);//最大连接数
        poolConfig.setMaxWaitMillis(2000);//最大等待毫秒数
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);

        RedisStandaloneConfiguration rsConfig = connectionFactory.getStandaloneConfiguration();
        rsConfig.setHostName("localhost");
        rsConfig.setPort(6379);
        this.connectionFactory = connectionFactory;
        return connectionFactory;
    }


    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate()
    {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        RedisSerializer stringRedisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setConnectionFactory(initRedisConnectionFacotry());
        return redisTemplate;
    }

    //偏底层，使用RedisCallback
    public void useRedisCallback(RedisTemplate redisTemplate)
    {
        redisTemplate.execute((RedisCallback) redisConnection -> {
            redisConnection.set("key1".getBytes(), "value1".getBytes());
            redisConnection.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
            return null;
        });
    }

    //更加容易理解-使用SessionCallback
    public void useSessionCallback(RedisTemplate redisTemplate)
    {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.opsForValue().set("key1", "value1");
                redisOperations.opsForHash().put("hash", "hashKey", "hashValue");
                return null;
            }
        });
    }


    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);

        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
//        redisTemplate.opsForValue().set("key1", "value1");
//        redisTemplate.opsForHash().put("hash", "hashKey", "hashValue");
    }
}
