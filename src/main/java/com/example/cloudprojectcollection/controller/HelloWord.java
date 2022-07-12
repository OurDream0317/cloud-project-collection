package com.example.cloudprojectcollection.controller;

import com.example.cloudprojectcollection.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Wwx
 * @createTime: 2022年06月28日
 * @version: 0.0.1
 * @Description: 测试
 */

@RestController
@RequestMapping("test")
public class HelloWord {

    @Autowired
    private TestService testService;

    @RequestMapping("/hello")
    public List hello() {
        System.out.println("------------------------");
        return testService.queryList();
    }

}

