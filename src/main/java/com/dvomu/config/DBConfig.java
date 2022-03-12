package com.dvomu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据源1配置类
 * 用于读取配置文件自定义配置
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.datasource.druid.master")
public class DBConfig {

    private String url;
    private String username;
    private String password;


}