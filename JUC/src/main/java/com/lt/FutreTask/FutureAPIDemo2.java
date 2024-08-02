package com.lt.FutreTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureAPIDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        long startTime = System.currentTimeMillis();
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(5);

            return "任务执行结束";
        });

        Thread thread = new Thread(futureTask, "t1");
        thread.start();


        System.out.println(Thread.currentThread().getName() + "\t  程序执行其它的任务");

        while (true){
            if (futureTask.isDone()){
                String message = futureTask.get();
                System.out.println(message);
                break;
            }else {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("不要再催了，越催越慢，再催挂掉");
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("执行耗时："+(endTime - startTime) / 1000 + "秒");
    }
}
