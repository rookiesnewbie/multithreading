package com.lt.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterruptDemo {

    static volatile boolean isStop = false;
    static AtomicBoolean  atomicBoolean = new AtomicBoolean(false);
    public static void main(String[] args){
        //InterruptDemo.m1_volatile();
        InterruptDemo.m2_atomicBoolean();

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
}
