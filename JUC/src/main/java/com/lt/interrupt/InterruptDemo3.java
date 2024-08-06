package com.lt.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo3 {

    public static void main(String[] args){

        InterruptDemo3.m_api();


    }

    /**
     * 通过API 中断运行中的线程
     *
     * 实例方法interrupt()仅仅是设置线程的中断状态位设置为true，不会停止线程
     */
    public static void m_api(){
        Thread t1 = new Thread(() -> {
           while (true){
               if (Thread.currentThread().isInterrupted()){
                   System.out.println(Thread.currentThread().getName() + "线程----------的中断标志为："+ Thread.currentThread().isInterrupted() + "+,程序结束");
                   break;
               }

               try {
                   Thread.sleep(200);
               } catch (InterruptedException e) {
                   /**
                    * 如果没有抛出异常，会造成死循环，需要再次调用Thread.currentThread().interrupt();
                    *
                    * 如果抛出异常，则不需要再次调用Thread.currentThread().interrupt();
                    */
                   Thread.currentThread().interrupt();
                   e.printStackTrace();
                   //throw new RuntimeException(e);
               }

               System.out.println("hello api");
           }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->{
            t1.interrupt();
        },"t2").start();
    }
}
