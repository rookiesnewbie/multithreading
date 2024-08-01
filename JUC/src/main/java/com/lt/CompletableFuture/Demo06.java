package com.lt.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author LiTeng
 * @Date 2023/10/2 12:08
 * Version 1.0
 * @Description 多任务组合
 */
public class Demo06 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // 线程1
        CompletableFuture<Integer> futureA =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName()+"--begin..");
                    int res = 100;
                    System.out.println("一："+res);
                    System.out.println(Thread.currentThread().getName()+"--over..");
                    return res;
                },executorService);

        // 线程2
        CompletableFuture<Integer> futureB =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName()+"--begin..");
                    int res = 30;
                    System.out.println("二："+res);
                    System.out.println(Thread.currentThread().getName()+"--over..");
                    return res;
                },executorService);

        CompletableFuture<Void> all = CompletableFuture.allOf(futureA,futureB);
        all.get();
        System.out.println("over....");
    }

}
