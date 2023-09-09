package com.lt;

public class ThreadRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "我是子线程");

        try {
            //让子线程阻塞3秒钟
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "子线程执行完毕");

    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "我是主线程");

        //启动线程
        new Thread(new ThreadRunnable()).start();
        new Thread(new ThreadRunnable()).start();

        System.out.println("主线程执行完毕");

    }
}