package com.example.cloudprojectcollection.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @Author: Wwx
 * @createTime: 2022年11月10日
 * @version: 0.0.1
 * @Description: 加锁方式：synchronized（object）传入对象，不同对象代表不同锁，可以在线程外部新建对象。
 * 首先要明确一个概念，一个 Java 类可以有很多个对象，但只有一个Class对象
 * 所谓的类锁，就是将 class 对象作为锁资源
 * 类锁只能在同一时刻被一个对象拥有
 * 类锁有两种写法:
 * synchronized 修饰静态方法
 * 指定锁为 class 对象
 */
@Slf4j
public class SynchronizedDemo implements Runnable{

    static SynchronizedDemo s1 = new SynchronizedDemo();
    static SynchronizedDemo s2 = new SynchronizedDemo();

    @Override
    public void run() {
        method();
    }

    public  void method() {
        synchronized (SynchronizedDemo.class) {
            final String name = Thread.currentThread().getName();
            log.info("类锁的第一种形式 " + name);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(name + " 运行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread(s1);
        final Thread t2 = new Thread(s2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
