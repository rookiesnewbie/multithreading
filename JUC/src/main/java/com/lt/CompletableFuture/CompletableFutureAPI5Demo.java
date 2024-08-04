package com.lt.CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureAPI5Demo {
    /**
     *
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        
        //CompletableFutureAPI5Demo.apiThenCombine1();
        CompletableFutureAPI5Demo.apiThenCombine2();
    }


    /**
     * 有返回值 supplyAsync  thenCombine: 谁快用谁（拆分式）
     */
    public static void apiThenCombine1() throws ExecutionException, InterruptedException {

        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try{
            //使用自定义的线程池
            CompletableFuture<Integer> taskA = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("任务一启动");
                    return 10;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            //使用自定义的线程池
            CompletableFuture<Integer> taskB = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("任务一启动");
                    return 20;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            CompletableFuture<Integer> result = taskA.thenCombine(taskB, (a, b) ->{
                System.out.println("最终合并结果");
                return a + b;
            });
            System.out.println(result.join());


        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            executorService.shutdown();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("最终耗时：" + (endTime - startTime) / 1000 + "秒");
    }


    /**
     * 有返回值 supplyAsync  thenCombine: 谁快用谁（表达式）
     */
    public static void apiThenCombine2() throws ExecutionException, InterruptedException {

        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try{
            //使用自定义的线程池
            CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("任务一启动");
                    return 10;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).thenCombine(CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("任务二启动");
                    return 20;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }),(a,b) ->{
                System.out.println("最终合并结果一为：" + (a + b));
                return a + b;
            }).thenCombine(CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("任务三启动");
                    return 30;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }),(c,d) ->{
                System.out.println("最终合并结果二为：" + (c + d));
                return c + d;
            });


            System.out.println("最终合并结果为:"+result.join());


        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            executorService.shutdown();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("最终耗时：" + (endTime - startTime) / 1000 + "秒");
    }
}
