package com.example.mybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName MybatisDemoApplication.java
 * @Description 测试的呀
 * @createTime 2021年10月29日 17:13
 */
@MapperScan(basePackages = {"com.example.mybatisdemo.mapper"})
@EnableTransactionManagement
@SpringBootApplication
public class DataSourcePoolServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSourcePoolServiceApplication.class, args);
    }

}
