package com.example.mybatisdemo.constant;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName Constants.java
 * @Description 系统常量
 * @createTime 2021年10月29日 17:28
 */
public class Constants {

    /**
     * 数据源参数模板：只有URL的信息不同其他的都相同
     */
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";
    public static final String URL = "jdbc:mysql://192.168.1.153:3306/%s?serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true&characterEncoding=utf-8";

    /**
     * 存储数据源的 hash key
     */
    public static final String DYNAMIC_TENANT_KEY = "tps_dynamic_%s";

}
