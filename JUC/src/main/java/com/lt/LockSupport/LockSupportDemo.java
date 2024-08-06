package com.lt.LockSupport;

import com.sun.org.apache.xpath.internal.objects.XObject;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {

    public static void main(String[] args) {
        //LockSupportDemo.waitAndNotify();
        //LockSupportDemo.awaitAndSignal();
        LockSupportDemo.parkAndUnpark();
    }

    /**
     * 注意：wait和notify方法必须要在同步块或者方法里面，且成对出现使用先wait后notify才可以
     */
    public static void waitAndNotify(){
        Object object = new Object();

        Thread t1 = new Thread(() -> {
            System.out.println("----------- come in");
            synchronized (object) {
                try {
                    System.out.println(Thread.currentThread().getName() + " \t 处于等待状态");
                    object.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + " \t 被唤醒了");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() ->{
           synchronized (object){
               System.out.println(Thread.currentThread().getName() + "\t 正在唤醒了t1线程");
               try {
                   TimeUnit.SECONDS.sleep(5);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               object.notify();
           }
        }).start();

    }


    /**
     * Conation中的线程等待和唤醒方法，需要先获取锁，一定要先await后signal，不要反了
     */
    public static void awaitAndSignal(){
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            System.out.println("----------- come in");
            try{
                System.out.println(Thread.currentThread().getName() + " \t 处于等待状态");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " \t 被唤醒了");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }, "t1").start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() ->{
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + "\t 正在唤醒了t1线程");

                TimeUnit.SECONDS.sleep(5);
                condition.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }

        },"t2").start();

    }


    /**
     * LockSupport类可以阻塞当前线程以及唤醒指定被阻塞的线程
     */
    public static void parkAndUnpark(){

        Thread t1 = new Thread(() -> {
            System.out.println("----------- come in");
            System.out.println(Thread.currentThread().getName() + " \t 处于等待状态");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " \t 被唤醒了");

        }, "t1");

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "\t 正在唤醒了t1线程");
            LockSupport.unpark(t1);  //唤醒t1线程
            try{
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        },"t2").start();

    }
}
