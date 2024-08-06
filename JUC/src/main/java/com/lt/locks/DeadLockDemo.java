package com.lt.locks;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    public static void main(String[] args) {
        final Object objectA = new Object();
        final Object objectB = new Object();

        new Thread(() ->{
            synchronized (objectA){
                System.out.println(Thread.currentThread().getName() + "\t 持有A锁，试图获取B锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (objectB){
                    System.out.println(Thread.currentThread().getName() + "\t 获取B锁成功");
                }
            }
        },"t1").start();

        new Thread(() ->{
            synchronized (objectB){
                System.out.println(Thread.currentThread().getName() + "\t 持有B锁，试图获取A锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (objectA){
                    System.out.println(Thread.currentThread().getName() + "\t 获取A锁成功");
                }
            }
        },"t2").start();
    }
}
