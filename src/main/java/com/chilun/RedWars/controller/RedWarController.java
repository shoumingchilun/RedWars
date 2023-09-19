package com.chilun.RedWars.controller;

import com.chilun.RedWars.service.RedWarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/give")
    public void give(@RequestParam BigDecimal sum,@RequestParam int peopleCount){

    }

    @PostMapping("/snatch")
    public BigDecimal snatch(@RequestParam String userId){
        return null;
    }

}
