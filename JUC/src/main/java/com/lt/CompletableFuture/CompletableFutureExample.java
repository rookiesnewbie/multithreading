package com.lt.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/**
 * @Author LiTeng
 * @Date 2023/10/3 8:19
 * Version 1.0
 * @Description
 */
public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = MyThreadPoolExecutor.newFixedThreadPool(2, 2, 2);
        long start = System.currentTimeMillis();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "Hello";
        },executorService);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
           return  "World";
        },executorService);

//        // 组合两个CompletableFuture的结果
//        CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 + " " + result2);
//
//        // 异步地等待组合后的结果
//        combinedFuture.thenAccept(result -> System.out.println("Combined Result: " + result));
//
//        // 等待异步任务完成，否则主线程会提前退出
//        combinedFuture.join();


        String result = future1.get() + future2.get();
        CompletableFuture.allOf(future1,future2).join();

        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("耗时："+(end - start));

    }

}
