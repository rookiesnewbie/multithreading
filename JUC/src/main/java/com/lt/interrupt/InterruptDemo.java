package com.lt.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {

     static volatile boolean isStop = false;
    public static void main(String[] args){
        InterruptDemo.m1_volatile();

    }

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
}
