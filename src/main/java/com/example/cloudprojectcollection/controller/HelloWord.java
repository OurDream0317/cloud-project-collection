package com.example.cloudprojectcollection.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Wwx
 * @createTime: 2022年06月28日
 * @version: 0.0.1
 * @Description: 测试
 */

@RestController
public class HelloWord {

    @RequestMapping("/hello")
    public String hello() {
        return "hello word";
    }

}

