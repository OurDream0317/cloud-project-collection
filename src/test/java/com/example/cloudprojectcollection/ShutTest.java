package com.example.cloudprojectcollection;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Wwx
 * @createTime: 2022年09月30日
 * @version: 0.0.1
 * @Description:
 */

public class ShutTest {

    /**
     * 在使用线程池的时候,ExecutorService提供了两种关闭线程池的方法,shutdown()和shutdownNow(),先不墨迹,直接写出这两个方法的作用:
     *
     * 使用shutdown正常关闭
     * 将线程池状态置为SHUTDOWN,线程池并不会立即停止：
     *
     * 停止接收外部submit的任务
     * 内部正在跑的任务和队列里等待的任务，会执行完
     * 等到第二步完成后，才真正停止
     *
     *
     * 使用shutdownNow强行关闭
     * 将线程池状态置为STOP。企图立即停止，事实上不一定：
     *
     * 跟shutdown()一样，先停止接收外部提交的任务
     * 忽略队列里等待的任务
     * 尝试将正在跑的任务interrupt中断
     * 返回未执行的任务列表
     */
    @Test
    public void m() {
        //核心池3,最大池6
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
                6,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));
        //创建5个线程
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
        executor.submit(() -> {
            System.out.println("after shutdown");
        });
    }

    @Test
    public void mm(){
        //核心池3,最大池6
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));
        //创建8个线程
        for (int i= 0;i<5;i++){
            executor.submit(()->{
                System.out.println(Thread.currentThread().getName());

            });
        }
        List<Runnable> runnables = executor.shutdownNow();
        System.out.println(runnables);
        executor.submit(()->{
            System.out.println("after shutdown");
        });
    }

    @Test
    public void mmm() throws InterruptedException {
        //核心池3,最大池6
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));
        //创建8个线程
        for (int i= 0;i<4;i++){
            executor.submit(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+"interrupted");
                }
            });
        }
        boolean b = executor.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println(b);
        System.out.println("after awaitTermination");
        executor.submit(()->{
            System.out.println("after shutdown");
        });
        executor.shutdown();
        boolean a = executor.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println(a);

    }
}
