package com.lt.CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureAPI3Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        //CompletableFutureAPI3Demo.apiThenAccept();
        CompletableFutureAPI3Demo.apiOrder();
    }


    /**
     * 有返回值 supplyAsync  thenAccept: 接收任务的处理结果，并消费处理，无返回结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void apiThenAccept() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try{
            //使用自定义的线程池
           CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("按部就班----> 第一步");
                return 1;
            },executorService).thenApply(f ->{
               System.out.println("按部就班----> 第二步");
               //int i = 10 / 0;
               return f + 1;
           }).thenApply(f ->{
               System.out.println("按部就班----> 第三步");
               return f + 1;
           }).thenAccept(result -> System.out.println(result));
        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            executorService.shutdown();
        }

        System.out.println(Thread.currentThread().getName() + "\t" + "去忙其它的业务操作");
    }


    /**
     * thenRun、thenAccept、thenApply之间的执行顺序：
     * - thenRun：任务A执行完执行B，并且B不需要A的结果
     * - thenAccept：任务A执行完执行B，B需要A的结果，但是任务B无返回值
     * - thenApply：任务A执行完执行B，B需要A的结果，同时任务B有返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void apiOrder() throws ExecutionException, InterruptedException {
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() ->{}).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(r -> System.out.println(r)).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r).join());
    }
}
