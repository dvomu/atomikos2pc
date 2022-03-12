package com.dvomu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据源2配置类
 * 用于读取配置文件自定义配置
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.datasource.druid.slave")
public class DBConfig2 {

    private String url;
    private String username;
    private String password;

}