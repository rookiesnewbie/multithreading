package com.lt;

public class ThreadRunnable{
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"我是主线程");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +"我是子线程");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("子线程执行结束");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +"我是子线程");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("子线程执行结束");
            }
        }).start();

        System.out.println("主线程执行结束");
    }




}