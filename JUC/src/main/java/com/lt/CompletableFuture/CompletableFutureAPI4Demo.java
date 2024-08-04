package com.lt.CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureAPI4Demo {
    /**
     *
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        
        CompletableFutureAPI4Demo.apiApplyToEither();
    }


    /**
     * 有返回值 supplyAsync  applyToEither: 谁快用谁
     */
    public static void apiApplyToEither() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try{
            //使用自定义的线程池
            CompletableFuture<String> plyerA = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("选手plyerA用时3秒");
                    return "plyerA";
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            //使用自定义的线程池
            CompletableFuture<String> plyerB = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("选手plyerB用时5秒");
                    return "plyerB";
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            //从plyerA、plyerB两个任务中获取结果，谁处理快就返回谁的结果
            CompletableFuture<String> result = plyerA.applyToEither(plyerB, (f) -> f + " is winer");
            System.out.println(result.get());


        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            executorService.shutdown();
        }

    }
    
}
