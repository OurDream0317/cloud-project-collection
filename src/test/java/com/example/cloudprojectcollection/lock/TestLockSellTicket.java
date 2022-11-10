package com.example.cloudprojectcollection.lock;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Wwx
 * @createTime: 2022年11月10日
 * @version: 0.0.1
 * @Description: Lock（比synchronized要轻量级）
 * 新建锁对象Lock l = new ReentrantLock();
 * 加锁 l.lock()
 * 解锁 l.unlock()
 *
 *
 */

public class TestLockSellTicket extends Thread {
    //票的数量
    private static  int  tick = 5;

    //锁
    private static ReentrantLock lock = new ReentrantLock();

    @SneakyThrows  //和try catch  一样
    @Override
    public void run() {
        //获取锁 锁等待1秒就返回false
        if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
            try {
                //获取到锁调用减库存方法
                sell();
            } finally {
                lock.unlock();
            }
        }else{
            System.out.println("目前访问人数较多，请稍后再试");
        }
    }

    /**
     * 减库存方法
     */
    private void sell() {
        System.out.println("现在还有："+tick);
        //判断库存是否足够
        if (tick>0){
            tick = tick - 1;
            System.out.println(Thread.currentThread().getName()+"卖了一张，还剩余"+tick);
        }else{
            System.out.println("票已经卖完了");
        }
    }

    /**
     * 模拟客户端买票
     * @param args
     */
    public static void main(String[] args) {
        TestLockSellTicket test = new TestLockSellTicket();
        //模拟十个客户端抢两张票
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(test);
            thread.setName("窗口"+i);
            thread.start();
        }
    }
}
