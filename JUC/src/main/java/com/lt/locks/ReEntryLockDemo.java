package com.lt.locks;

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
        ReEntryLockDemo.m22();


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
}
