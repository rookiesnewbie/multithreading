package com.lt.CompletableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class FutureThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //FutureThreadPoolDemo.m1();

        Map<String, Object> map = FutureThreadPoolDemo.m2();
        System.out.println(map);

    }

    /**
     * 没有使用多线程实现 （耗时10秒） 即只由一个线程出处理多个耗时任务
     * @throws InterruptedException
     */
    public static void m1() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        TimeUnit.SECONDS.sleep(3); //耗时业务操作一
        TimeUnit.SECONDS.sleep(5); //耗时业务操作二
        TimeUnit.SECONDS.sleep(2); //耗时业务操作三

        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时："+(endTime - startTime) / 1000 + "秒");

        System.out.println("任务执行结束");
    }

    /**
     * 使用多线程实现 （耗时5秒） 即只由多个线程出处理多个耗时任务
     * @return
     */
    public static Map<String,Object> m2() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        //创建3个固定的线程数
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(3);

            return "执行了耗时任务一";
        });

        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(5);

            return "执行了耗时任务二";
        });


        FutureTask<String> futureTask3 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(2);

            return "执行了耗时任务三";
        });
        executorService.submit(futureTask1);

        executorService.submit(futureTask2);

        executorService.submit(futureTask3);

        Map<String, Object> map = new HashMap<>();
        map.put(Thread.currentThread().getName()+1,futureTask1.get());
        map.put(Thread.currentThread().getName()+2,futureTask2.get());
        map.put(Thread.currentThread().getName()+3,futureTask3.get());

        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时："+(endTime - startTime) / 1000 + "秒");

        System.out.println("任务执行结束");

        return map;
    }
}
