package com.example.mybatisdemo.config;

import lombok.Data;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName DrudConfigProperties.java
 * @Description 数据源非必须项配置
 * @createTime 2021年10月29日 17:51
 */
@Data
public class DrudConfigProperties {

    private Integer initialSize = 1;
    private Integer maxActive = 1;
    private Integer minIdle = 1;
    private Integer maxWait = 60000;
    private Long timeBetweenEvictionRunsMillis = 60000L;
    private Long timeBetweenLogStatsMillis = 300000L;
    private Boolean testWhileIdle = true;
    private Boolean testOnBorrow = false;
    private Boolean testOnReturn = false;
    private String validationQuery = "select 'x'";
    private Boolean poolPreparedStatements = false;
}
