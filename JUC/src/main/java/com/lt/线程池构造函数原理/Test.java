package com.lt.线程池构造函数原理;

import java.util.concurrent.ExecutorService;

/**
 * @Author LiTeng
 * @Date 2023/10/2 14:43
 * Version 1.0
 * @Description 测试
 */
public class Test {
    public static void main(String[] args) {

        //1.提交的线程务数数 < 核心线程数 （核心线程数任务复用）
        //2.提交的线程任务数 > 核心线程数 且队列容量（核心线程数 +最大线程数 ）没有满，则将该任务缓存到我们的队列中 ，将循环3 4 5 6 7 缓存到队列中
        //3.提交的线程任务数 > 核心线程数 且队列容量已满了,这里 8,9,10已满，最多在额外创建两个线程 （4-2） 2个线程(即最大线程数 - 核心线程数)
        // 2个线程  8,9
        //第10个  ----->   任务拒绝服务
        ExecutorService executorService = MyThreadPoolExecutor.newFixedThreadPool(2, 4, 5);
        for (int i = 0; i <= 10; i++) {
            final  int num = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"------>"+num);
                }
            });
        }

        //实际线程数 = 核心线程数 + 缓存队列的容量 + （最大线程数 - 核心线程数）
    }

}
