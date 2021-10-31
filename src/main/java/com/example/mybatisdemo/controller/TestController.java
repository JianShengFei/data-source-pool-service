package com.example.mybatisdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianshengfei
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description 测试的呀
 * @createTime 2021年10月29日 17:13
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("sayHelloWorld")
    public String sayHelloWorld() {
        return "Hello World";
    }


}
