package com.lt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+ "我是主线程");

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+ "我是子线程");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+"子线程执行结束");
            }
        });

        System.out.println(Thread.currentThread().getName()+"子线程执行结束");
    }
}