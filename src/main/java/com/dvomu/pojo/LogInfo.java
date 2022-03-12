package com.dvomu.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class LogInfo {
    private Integer id;
    private Date createTime;
    private String content;
}