package com.lt;

import java.util.concurrent.TimeUnit;

public class DaemonDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 开始运行，"+(Thread.currentThread().isDaemon()? "守护线程":"用户线程"));
            while (true){

            }
        },"t1");
        thread1.setDaemon(true); //false为用户线程，true为守护线程，默认为false
        thread1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + "\t ---- 主线程结束");
    }

}
