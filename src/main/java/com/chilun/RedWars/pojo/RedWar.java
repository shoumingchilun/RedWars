package com.chilun.RedWars.pojo;



import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @auther 齿轮
 * @create 2023-09-19-11:03
 */
@Data
public class RedWar {
    private String id;
    private BigDecimal sum;
    private int peopleCountLimit;
    private Date endTime;
}
