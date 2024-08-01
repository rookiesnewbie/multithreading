package com.lt.CompletableFuture;

import com.lt.线程池构造函数原理.MyExecutionHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author LiTeng
 * @Date 2023/10/2 14:37
 * Version 1.0
 * @Description 自定义线程池
 */
public class MyThreadPoolExecutor {
    public static ExecutorService newFixedThreadPool(int corePollSize,int maxPoolSize,int blockingQueue){
        return new ThreadPoolExecutor(corePollSize,maxPoolSize,
                60L, TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue<Runnable>(blockingQueue),
                new MyExecutionHandler());

    }

}
