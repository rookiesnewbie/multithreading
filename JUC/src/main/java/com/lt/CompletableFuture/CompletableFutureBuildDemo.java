package com.lt.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureBuildDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureBuildDemo.m1();

        CompletableFutureBuildDemo.m2();
    }


    /**
     * 无返回值  runAsync
     */
    public static void m1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //使用默认提供的线程池
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> System.out.println(Thread.currentThread().getName()));


        //使用自定义的线程池
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> System.out.println(Thread.currentThread().getName()),executorService);
        System.out.println(completableFuture1.get());
        System.out.println(completableFuture2.get());
    }


    /**
     * 有返回值 supplyAsync
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void m2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //使用默认提供的线程池
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "使用默认提供的线程池";
        });


        //使用自定义的线程池
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "使用自定义的线程池";
        },executorService);
        System.out.println(completableFuture1.get());
        System.out.println(completableFuture2.get());
    }
}
