package com.lt.synchronize;

public class ThreadCount implements Runnable {

    private static   int count = 100;
    public static void main(String[] args) {
        ThreadCount threadCount = new ThreadCount();

        /**
         * 两个线程同时对count变量做"--"操作
         */
        new Thread(threadCount).start();
        new Thread(threadCount).start();

    }

    @Override
    public  void run() {

        while (true){
            if (count > 1){
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                /*synchronized(this){
                    count--;
                    System.out.println(Thread.currentThread().getName()+"------》"+count);
                }*/

                call();

            }
        }
    }

    public synchronized void cal(){
        count--;
        System.out.println(Thread.currentThread().getName()+"------》"+count);
    }

    public static void call(){
        synchronized (ThreadCount.class){
            count--;
            System.out.println(Thread.currentThread().getName()+"------》"+count);
        }
    }
}