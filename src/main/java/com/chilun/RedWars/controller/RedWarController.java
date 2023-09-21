package com.chilun.RedWars.controller;

import com.chilun.RedWars.service.RedWarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

/**
 * @auther 齿轮
 * @create 2023-09-19-11:08
 */
@RestController
@RequestMapping("/redwar")
public class RedWarController {//思路：使用list记录每个红包被分配的金额，使用hash记录每个人抢红包的金额
    @Autowired
    RedWarService service;

    @PostMapping("/send")//返回生成红包的uuid
    public String send(@RequestParam BigDecimal sum, @RequestParam int peopleCount) {
        //输入红包金额和抢红包人数
        //1、生成红包的UUid
        String id = UUID.randomUUID().toString();
        // 2、计算好每个红包金额，使用二倍均值算法
        int sum2 = sum.multiply(BigDecimal.valueOf(100)).intValue();
        int[] numbers = new int[peopleCount];
        int rest = sum2;
        for (int i = 0; i < peopleCount - 1; i++) {
            numbers[i] = (int) (new Random().nextDouble() * 2 * rest / (peopleCount - i));
            rest -= numbers[i];
        }
        numbers[peopleCount-1] = rest;
        // 3、使用list存储到redis数据库。
        service.send(id,numbers);
        return id;
    }

    @GetMapping("/snatch")//返回抢到红包的金额，如果已经抢过，返回"Has snatched."
    public String snatch(@RequestParam String userId, @RequestParam String RedWarID) {
        //输入用户id和红包id
        return service.snatch(userId,RedWarID);
    }

    @GetMapping("/query")//返回抢到红包的金额，仅查询（只有查询功能，就不做了）
    public BigDecimal query(@RequestParam String userId, @RequestParam String RedWarID) {
        return null;
    }

}
