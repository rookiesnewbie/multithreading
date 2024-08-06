package com.lt.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterruptDemo2 {

    public static void main(String[] args){

        InterruptDemo2.m_api();

    }

    /**
     * 通过API 中断运行中的线程
     *
     * 实例方法interrupt()仅仅是设置线程的中断状态位设置为true，不会停止线程
     */
    public static void m_api(){
        Thread t1 = new Thread(() -> {
            for (int i = 1; i < 300; i++){
                System.out.println("=====================> \t" + i);
            }
            System.out.println("t1线程调用interrupt()后的的中断标识02:" + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        System.out.println("t1线程默认的中断标识:" +t1.isInterrupted()); //fasle

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t1.interrupt();  //t1自己终止自己
        System.out.println("t1线程调用interrupt()后的的中断标识01:" + t1.isInterrupted()); //true

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("t1线程调用interrupt()后的的中断标识03:" + t1.isInterrupted()); //fasle
    }
}
