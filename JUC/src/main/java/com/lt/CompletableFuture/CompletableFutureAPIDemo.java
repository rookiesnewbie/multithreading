package com.lt.CompletableFuture;

import java.util.concurrent.*;

public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {


        //CompletableFutureAPIDemo.apiGet();
        //CompletableFutureAPIDemo.apiGetTimeout();
        //CompletableFutureAPIDemo.apiGetNow();
        //CompletableFutureAPIDemo.apiJoin();
        CompletableFutureAPIDemo.apiComplete();
    }


    /**
     * 有返回值 supplyAsync  get一直等待
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void apiGet() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try{
            //使用自定义的线程池
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
                return "使用get方法，没有指定超时时间获取结果API";
            },executorService);
            System.out.println(completableFuture.get());
        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            executorService.shutdown();
        }
    }


    /**
     * 有返回值 supplyAsync  get过时不候
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void apiGetTimeout() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            //使用自定义的线程池
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
                return "使用get方法，指定超时时间获取结果API";
            },executorService);
            System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            executorService.shutdown();
        }
    }


    /**
     * 有返回值 supplyAsync   getNow(T valueIfAbsent): 表示立刻获取异步结果，如果异步任务没有完成，则立即返回getNow参数的值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void apiGetNow() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            //使用自定义的线程池
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
                return "使用join方法，获取结果API";
            },executorService);
            //TimeUnit.SECONDS.sleep(3);
            System.out.println(completableFuture.getNow("任务还没有执行完成"));
        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            executorService.shutdown();
        }
    }

    /**
     * 有返回值 supplyAsync  join 只能无限期地等待异步操作完成，没有提供超时机制。异常以未检查异常的形式抛出
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void apiJoin() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            //使用自定义的线程池
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //int i = 10 / 0;
                System.out.println(Thread.currentThread().getName());
                return "使用join方法，获取结果API";
            },executorService);
            System.out.println(completableFuture.join());
        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            executorService.shutdown();
        }
    }

    /**
     * 有返回值 supplyAsync   complete(T value): 表示立刻检查异步任务是否完成，如果没有完成，则将complete参数的值替换成异步任务的结果，结果为true表示任务没有完成，false表示已完成
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void apiComplete() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            //使用自定义的线程池
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
                return "使用join方法，获取结果API";
            },executorService);
            TimeUnit.SECONDS.sleep(5);
            //System.out.println(completableFuture.complete("进行中") + "\t" + completableFuture.join());
            System.out.println(completableFuture.complete("进行中") + "\t" + completableFuture.get());
        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            executorService.shutdown();
        }
    }

}
