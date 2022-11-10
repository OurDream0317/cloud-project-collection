package com.example.cloudprojectcollection.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Wwx
 * @createTime: 2022年09月09日
 * @version: 0.0.1
 * @Description: 排它锁，又称独占锁，独享锁 synchronized就是一个排它锁
 *               共享锁，又称为读锁，获得共享锁后，可以查看，但无法删除和修改数 据， 其他线程此时业获取到共享锁，也可以查看但是 无法修改和 删除数据
 *               共享锁和排它锁典型是ReentranReadWriteLock 其中，读锁是共享锁，写锁是 排它锁
 */

public class ReentranReadWriteLockDemo {

    //创建读写锁
    //是否是公平策略 ，和 ReentranLock 一样 传入true ，false
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    //创建读锁
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    //创建写锁
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    //读锁方法
    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁");
            Thread.sleep(1000);
        } catch (Exception e) {

        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }


    //写锁方法
    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁");
            Thread.sleep(1000);
        } catch (Exception e) {

        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }


    public static void main(String[] args) {
        new Thread(()->read(),"T1").start();
        new Thread(()->read(),"T2").start();
        new Thread(()->read(),"T3").start();
        new Thread(()->read(),"T4").start();
        new Thread(()->read(),"T5").start();
        new Thread(()->read(),"T6").start();
        new Thread(()->write(),"T7").start();
        new Thread(()->write(),"T8").start();
    }

}
