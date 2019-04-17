package com.example.springboot.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

@Controller
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate; //该instance会自动生成

    @RequestMapping("/stringAndHash")
    @ResponseBody
    public Map<String, Object> testStringAndHash() {
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("int_key", "1");
        stringRedisTemplate.opsForValue().set("int", "1");
        stringRedisTemplate.opsForValue().increment("int");

        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");

        Map<String, String> hash = new HashMap<>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");

        stringRedisTemplate.opsForHash().putAll("hash", hash);
        stringRedisTemplate.opsForHash().put("hash", "field3", "value3");
        BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash");
        hashOps.delete("field1", "field2");
        hashOps.put("field4", "value5");

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> testList()
    {
        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3", "v4", "v5", "v6");
        BoundListOperations listOps = stringRedisTemplate.boundListOps("list2");

        Object result1 = listOps.rightPop();
        Object result2 = listOps.index(1);
        listOps.leftPush("v0");
        Long size = listOps.size();

        List elements = listOps.range(0, size - 2);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/set")
    @ResponseBody
    public Map<String, Object> testSet()
    {
        stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4", "v5");
        stringRedisTemplate.opsForSet().add("set2", "v2", "v4", "v6", "v8");
        BoundSetOperations setOps = stringRedisTemplate.boundSetOps("set1");
        setOps.add("v6", "v7");
        setOps.remove("v1", "v7");

        Set set1 = setOps.members();
        Long size = setOps.size();

        Set inter = setOps.intersect("set2");
        setOps.intersectAndStore("set2", "inter");

        Set diff = setOps.diff("set2");
        setOps.diffAndStore("set2", "diff");

        Set union = setOps.union("set2");
        setOps.unionAndStore("set2", "union");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }


    @RequestMapping("/zset")
    @ResponseBody
    public Map<String, Object> testZSet()
    {
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();

        for (int i = 0; i <=9; i++)
        {
            double score = i * 0.1;
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTupleSet.add(typedTuple);
        }

        stringRedisTemplate.opsForZSet().add("zset1", typedTupleSet);
        BoundZSetOperations<String, String> zsetOps = stringRedisTemplate.boundZSetOps("zset1");

        zsetOps.add("value10", 0.26);
        Set<String> setRange = zsetOps.range(1,6);
        System.out.println(setRange);
        Set<String> setScore = zsetOps.rangeByScore(0.2, 0.6);
        System.out.println(setScore);
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt("value3");

        range.lte("value8");
        Set<String> setLex = zsetOps.rangeByLex(range);
        System.out.println(setLex);
        zsetOps.remove("value9", "value2");

        Double score = zsetOps.score("value8");
        Set<ZSetOperations.TypedTuple<String>> rangeSet = zsetOps.rangeWithScores(1, 6);
        System.out.println(rangeSet);
        Set<ZSetOperations.TypedTuple<String>> scoreSet = zsetOps.rangeByScoreWithScores(1, 6);
        System.out.println(scoreSet);
        Set<String> reverseSet = zsetOps.reverseRange(2, 8);
        System.out.println(reverseSet);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/multi")
    @ResponseBody
    public Map<String, Object> testMulti()
    {
        redisTemplate.opsForValue().set("key1", "value1");
        List list = (List)redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.watch("key1");
                // Start Redis Transaction
                redisOperations.multi();
                redisOperations.opsForValue().set("key2", "value2");
                System.out.println(redisOperations.opsForValue().get("key2"));// result is null as the code is still in the queue
                redisOperations.opsForValue().set("key3", "value3");
                return redisOperations.exec();
            }
        });

        System.out.println(list);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/pipeline")
    @ResponseBody
    public Map<String, Object> testPipeline()
    {
        Long start = System.currentTimeMillis();
        List list = (List)redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                for (int i = 1; i <= 10000; i++)
                {
                    redisOperations.opsForValue().set("pipeline_ " + i, "value_" + i);
                    String value = (String) redisOperations.opsForValue().get("pipeline_" + i);
                    if (i == 10000)
                    {
                        System.out.println("Code is just in the execute order, null for any value :" + value);
                    }
                }
                return null;
            }
        });

        Long end = System.currentTimeMillis();
        System.out.println("Time spent : " + (end - start));
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/lua")
    @ResponseBody
    public Map<String, Object> testLua()
    {
        DefaultRedisScript<String> rs = new DefaultRedisScript<>();
        rs.setScriptText("return 'Hello World'");
        rs.setResultType(String.class);
        RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
        String str = (String) redisTemplate.execute(rs, stringRedisSerializer, stringRedisSerializer, null);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/lua2")
    @ResponseBody
    public Map<String, Object> testLua2(String key1, String key2, String value1, String value2)
    {
        String lua = "redis.call('set', KEYS[1], ARGV[1] \n)"
                + "redis.call('set', KEYS[2], ARGV[2] \n)"
                + "local str1 = redis.call('get', KEYS[1]) \n"
                + "local str2 = redis.call('get', KEYS[2]) \n"
                + "if str1 == srt2 then \n"
                + "return 1 \n"
                + "end \n"
                + "return 0 \n";

        System.out.println(lua);
        DefaultRedisScript<Long> rs = new DefaultRedisScript<>();
        rs.setScriptText(lua);
        rs.setResultType(Long.class);
        RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();

        List<String> keyList = new ArrayList<>();
        keyList.add(key1);
        keyList.add(key2);

        Long result = (Long) redisTemplate.execute(rs, redisSerializer, redisSerializer, keyList, value1, value2);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

}
