package com.lt.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterruptDemo {

    static volatile boolean isStop = false;
    static AtomicBoolean  atomicBoolean = new AtomicBoolean(false);
    public static void main(String[] args){
        //InterruptDemo.m1_volatile();
        //InterruptDemo.m2_atomicBoolean();
        InterruptDemo.m3_api();

    }

    /**
     * 通过一个volatile变量实现 中断运行中的线程
     */
    public static void m1_volatile(){
        new Thread(() ->{
            while (true){
                if (isStop){
                    System.out.println(Thread.currentThread().isInterrupted() + "\t isStop被修改为true，程序停止");
                    break;
                }
                System.out.println("--------------  hello volatile");
            }
        },"t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() ->{
            isStop = true;  //停止线程
        },"t2").start();
    }


    /**
     * 通过一个atomicBoolean变量实现 中断运行中的线程
     */
    public static void m2_atomicBoolean(){
        new Thread(() ->{
            while (true){
                if (atomicBoolean.get()){
                    System.out.println(Thread.currentThread().isInterrupted() + "\t atomicBoolean被修改为true，程序停止");
                    break;
                }
                System.out.println("--------------  hello atomicBoolean");
            }
        },"t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() ->{
            atomicBoolean.set(true);  //停止线程
        },"t2").start();
    }


    /**
     * 通过API 中断运行中的线程
     */
    public static void m3_api(){
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().isInterrupted() + "\t isInterrupted被修改为true，程序停止");
                    break;
                }
                System.out.println("--------------  hello isInterrupted api");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //t1.interrupt();  //t1自己终止自己

        //t2向t1发出势商，将t1的中断标志位设为true希望t1停下来
        new Thread(() ->{
            t1.interrupt();  //停止线程
        },"t2").start();
    }
}
