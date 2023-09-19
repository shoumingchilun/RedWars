package com.chilun.RedWars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @auther 齿轮
 * @create 2023-09-19-11:08
 */
@Service
public class RedWarService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;



}
