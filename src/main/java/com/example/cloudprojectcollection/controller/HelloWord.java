package com.example.cloudprojectcollection.controller;

import com.example.cloudprojectcollection.service.TestService;
import io.netty.util.concurrent.CompleteFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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


    @RequestMapping("/getTree")
    public List getTree() {
        System.out.println("------------------------");
        return testService.getTree();
    }

    /**
     * 多线程异步执行DEMO
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("/getAsync")
    public List getAsync() throws ExecutionException, InterruptedException {
        System.out.println(LocalDate.now()+"-"+ LocalTime.now());
        ExecutorService executor = new ThreadPoolExecutor(4,5,5000l, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        List list = new ArrayList();
        CompletableFuture<Boolean> a = CompletableFuture.supplyAsync(() -> {
            try {
                getA(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Boolean.TRUE;
        },executor);

        CompletableFuture<Boolean> b = CompletableFuture.supplyAsync(() -> {
            try {
                getB(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Boolean.TRUE;
        },executor);

        CompletableFuture<Boolean> c = CompletableFuture.supplyAsync(() -> {
            try {
                getC(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Boolean.TRUE;
        },executor);

        CompletableFuture<Boolean> d = CompletableFuture.supplyAsync(() -> {
            try {
                getD(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Boolean.TRUE;
        },executor);

        a.get();
        b.get();
        c.get();
        d.get();
        System.out.println(LocalDate.now()+"-"+ LocalTime.now());
        return list;
    }


    public void getA(List list) throws InterruptedException {
        list.add("王卫翔");
    }

    public void getB(List list) throws InterruptedException {
        list.add("陈鹏元");
    }

    public void getC(List list) throws InterruptedException {
        list.add("张延顺");
    }

    public void getD(List list) throws InterruptedException {
        list.add("张文亮");
    }
}

