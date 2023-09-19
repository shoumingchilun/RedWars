package com.chilun.RedWars.controller;

import com.chilun.RedWars.service.RedWarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @auther 齿轮
 * @create 2023-09-19-11:08
 */
@RestController
@RequestMapping("/redwar")
public class RedWarController {
    @Autowired
    RedWarService service;

    @PostMapping("/give")//返回生成红包的uuid
    public String give(@RequestParam BigDecimal sum, @RequestParam int peopleCount) {
        return null;
    }

    @GetMapping("/snatch")//返回抢到红包的金额，如果已经抢过，返回-1
    public BigDecimal snatch(@RequestParam String userId, @RequestParam String RedWarID) {
        return null;
    }

    @GetMapping("/query")//返回抢到红包的金额，仅查询
    public BigDecimal query(@RequestParam String userId, @RequestParam String RedWarID) {
        return null;
    }

}
