package com.lt.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author LiTeng
 * @Date 2023/9/16 14:36
 * Version 1.0
 * @Description lock锁的基本使用
 */
public class ThreadLockCondition {

    private static int count = 100;
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();
    public static void main(String[] args) {

        ThreadLockCondition threadLock01 = new ThreadLockCondition();
        ThreadLockCondition threadLock02 = new ThreadLockCondition();
        threadLock01.print();
        threadLock02.print2();
        try {
            Thread.sleep(3000);
            threadLock01.call();

            Thread.sleep(2000);
            threadLock02.call();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }

    private void call(){
        try {
            lock.lock();
            condition.signal(); //唤醒被阻塞的线程  类似与notify
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void print(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (count > 50){
                        count--;
                        System.out.println(Thread.currentThread().getName() + "的count为：" + count);

                    }

                    condition.await(); //与synchronize的wait()方法类似，主动释放锁，同时当前线程处于阻塞状态

                    System.out.println(Thread.currentThread().getName() + "获得锁");


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }
            }
        }).start();
    }


    public  void print2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock(); //上锁
                    condition.await();

                    while (count <= 50 && count > 0){
                        count--;
                        System.out.println(Thread.currentThread().getName() + "的count为：" + count);

                    }

                    System.out.println(Thread.currentThread().getName() + "获得锁");

                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                }


            }
        }).start();
    }

}
