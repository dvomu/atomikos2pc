package com.dvomu.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfo {
    private Integer id;
    private Double money;
    private String userId;
    private String address;
    private Date createTime;
}