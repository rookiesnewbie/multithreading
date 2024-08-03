package com.lt.CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureUserDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //CompletableFutureUserDemo.m2();
        CompletableFutureUserDemo.m1();

        System.out.println(Thread.currentThread().getName() + "\t 线程出忙处理其它业务");
    }


    public static void m1(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);

       try {
           CompletableFuture.supplyAsync(() ->{
               int result;
               try {
                   TimeUnit.SECONDS.sleep(3);
                   //result = ThreadLocalRandom.current().nextInt(10);
                   result = ThreadLocalRandom.current().nextInt(10) / 0; //模拟异常
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               return result;
           },executorService).whenComplete((v,e) ->{
               /**
                * whenComplete表示任务执行结束后自动调用
                * v: 代表执行任务返回的结果
                * e: 代表执行任务抛出异常
                */
               if (null == e){
                   System.out.println("3秒后获取到的结果为"+v);
               }
           }).exceptionally(e ->{
               e.printStackTrace();
               System.out.println("执行任务发生异常");
               return null;
           });
       }catch (Exception e){
           System.out.println("出现异常");
       }finally {
           executorService.shutdown();
       }
    }

    /**
     * 有返回值 supplyAsync  get方法阻塞
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void m2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //使用默认提供的线程池
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
            return "使用默认提供的线程池";
        });


        //使用自定义的线程池
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
            return "使用自定义的线程池";
        },executorService);
        System.out.println(completableFuture1.get());
        System.out.println(completableFuture2.get());
    }
}
