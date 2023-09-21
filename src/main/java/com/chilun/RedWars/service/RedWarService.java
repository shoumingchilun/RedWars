package com.chilun.RedWars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.xml.datatype.Duration;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @auther 齿轮
 * @create 2023-09-19-11:08
 */
@Service
public class RedWarService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    private final String ResultRedWarPrefix = "ResultRedWar:";
    private final String SendRedWarPrefix = "SendRedWar:";

    public void send(String id, int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            stringRedisTemplate.opsForList().leftPush(SendRedWarPrefix + id,
                    BigDecimal.valueOf(numbers[i]).divide(BigDecimal.valueOf(100)).toString());
        }
        stringRedisTemplate.expire(SendRedWarPrefix + id, 1, TimeUnit.DAYS);
    }

    public String snatch(String userId, String redWarID) {
        //先判断红包是否为空，再判断是否已经抢过此红包，最后再记录抢红包
        String lua = "if ( redis.call('hexists',KEYS[2],KEYS[3]) == 1 ) then " + //说明已经抢过了
                " return '已经抢过了' " +
                " elseif ( redis.call('llen',KEYS[1]) == 0 ) then " +//说明已经抢完了
                " return '已经抢完了' " +
                " else " +//说明开始抢红包
                " local number = redis.call('lpop',KEYS[1]) " +
                " redis.call('hset',KEYS[2],KEYS[3],number) " +
                " return tostring(number) " +
                " end ";
//        System.out.println(lua);
        List<String> list = Arrays.asList(SendRedWarPrefix + redWarID, ResultRedWarPrefix + redWarID, userId);
//        System.out.println(list);
        return stringRedisTemplate.execute(new DefaultRedisScript<>(lua, String.class), list, "1");
    }
}
