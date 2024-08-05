package com.lt.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntryLockDemo {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + "\t come in");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t end");
    }

    public synchronized void m2(){
        System.out.println(Thread.currentThread().getName() + "\t come in");
        m3();
    }

    public synchronized void m3(){
        System.out.println(Thread.currentThread().getName() + "\t come in");
    }



    public static void main(String[] args) {

        //ReEntryLockDemo.m11();
        //ReEntryLockDemo.m22();
        ReEntryLockDemo.m33();


    }

    /**
     * 同步块
     */
    public static void m11(){
        final Object object = new Object();
        new Thread(() ->{
            synchronized (object){
                System.out.println(Thread.currentThread().getName() +"\t 外层调用");
                synchronized (object){
                    System.out.println(Thread.currentThread().getName() +"\t 中层调用");
                    synchronized (object){
                        System.out.println(Thread.currentThread().getName() +"\t 内层调用");
                    }
                }
            }
        },"t1").start();

        new Thread(() ->{
            synchronized (object){
                System.out.println(Thread.currentThread().getName() +"\t 外层调用");
                synchronized (object){
                    System.out.println(Thread.currentThread().getName() +"\t 中层调用");
                    synchronized (object){
                        System.out.println(Thread.currentThread().getName() +"\t 内层调用");
                    }
                }
            }
        },"t2").start();

    }

    /**
     * 同步方法
     */
    public static void m22(){
        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();
        new Thread(() ->{
            reEntryLockDemo.m1();
        },"t1").start();
    }


    /**
     * 隐式锁
     */
    public static void m33(){
        final Lock lock = new ReentrantLock();
        new Thread(() ->{
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() +"\t 外层调用");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() +"\t 中层调用");

                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() +"\t 内层调用");
                    }finally {
                        lock.unlock();
                    }
                }finally {
                    lock.unlock();
                }
            }finally {
               lock.unlock();
            }

        },"t1").start();
    }

}
