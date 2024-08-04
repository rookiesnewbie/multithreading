package com.lt.CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureAPI2Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        //CompletableFutureAPI2Demo.apiThenApply();
        CompletableFutureAPI2Demo.apiHandle();
    }


    /**
     * 有返回值 supplyAsync  thenApply: 计算结果存在依赖关系，这两个线程串行化，由于存在依赖关系(当前步错，不走下一步)，当前步骤有异常的话就叫停
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void apiThenApply() throws ExecutionException, InterruptedException {
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
           }).whenComplete((value,e) ->{
               if (null == e){
                   System.out.println("按部就班----> 完成");
                   System.out.println("按部就班最终的结果为：" + value);
               }
           }).exceptionally(e ->{
               System.out.println("按部就班过程中出现异常");
               return null;
            });
        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            executorService.shutdown();
        }

        System.out.println(Thread.currentThread().getName() + "\t" + "去忙其它的业务操作");
    }

    /**
     * 有返回值 supplyAsync  handle: 计算结果存在依赖关系，这两个线程串行化，若有异常也可以往下一步走，根据带的异常参数可以进一步处理
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void apiHandle() throws ExecutionException, InterruptedException {
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
            },executorService).handle((f,e) ->{
                int i = 10 / 0;
                System.out.println("按部就班----> 第二步");
                return f + 1;
            }).handle((f,e) ->{
                System.out.println("按部就班----> 第三步");
                return f + 1;
            }).whenComplete((value,e) ->{
                if (null == e){
                    System.out.println("按部就班----> 完成");
                    System.out.println("按部就班最终的结果为：" + value);
                }
            }).exceptionally(e ->{
                System.out.println("按部就班过程中出现异常");
                return null;
            });
        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            executorService.shutdown();
        }

        System.out.println(Thread.currentThread().getName() + "\t" + "去忙其它的业务操作");
    }

}
