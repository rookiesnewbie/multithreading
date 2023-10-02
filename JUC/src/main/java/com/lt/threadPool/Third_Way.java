package com.lt.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author LiTeng
 * @Date 2023/10/1 16:58
 * Version 1.0
 * @Description 方式三
 */
public class Third_Way {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int count = i;
            //创建10个线程
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+count);
                }
            });

        }

    }

}
