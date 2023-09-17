package com.lt.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author LiTeng
 * @Date 2023/9/16 14:36
 * Version 1.0
 * @Description lock锁的基本使用
 */
public class ThreadLock {

    private int count = 100;
    private Lock lock = new ReentrantLock();
    public static void main(String[] args) {

        ThreadLock threadLock = new ThreadLock();
        threadLock.print();
        threadLock.print2();
    }

    public void print(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock(); //上锁
                    while (count > 50){
                        count--;
                        System.out.println(Thread.currentThread().getName() + "的count为：" + count);

                    }
                    System.out.println(Thread.currentThread().getName() + "获得锁");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();//释放锁
                }
            }
        }).start();
    }


    public void print2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock(); //上锁
                while (count <= 50 && count > 0){
                    count--;
                    System.out.println(Thread.currentThread().getName() + "的count为：" + count);

                }

                System.out.println(Thread.currentThread().getName() + "获得锁");

            }
        }).start();
    }

}
