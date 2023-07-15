//package com.js.common.redisUtil;
//
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Objects;
//
//@Component
//public class RedisUtils {
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
//    @Resource
//    private RedissonClient redissonClient;
//    public void lPush(String key, Object element){
//        Long aLong = redisTemplate.opsForList().leftPush(key, element);
//    }
//
//    public String lIndex(String key, Integer index){
//        return (String) redisTemplate.opsForList().index(key, index);
//    }
//
//    public void setKey(String key, String value){
//        redisTemplate.opsForValue().set(key,value);
//    }
//
//}
