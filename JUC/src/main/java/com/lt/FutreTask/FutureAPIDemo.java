package com.lt.FutreTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureAPIDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        long startTime = System.currentTimeMillis();
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(5);

            return "任务执行结束";
        });

        Thread thread = new Thread(futureTask, "t1");
        thread.start();

        /**
         * 1、get容易导致阻塞，一般建议放在程序后面，一旦调用不见不散，非要等到结果才会离开，不管你是否计算完成，容易程序堵塞。
         * 2、假如我不愿意等待很长时间，我希望过时不候，可以自动离开
         */
        //String message = futureTask.get();
        //System.out.println(message);

        System.out.println(Thread.currentThread().getName() + "\t  程序执行其它的任务");

        //String message = futureTask.get();
        //System.out.println(message);

        /**
         *
         * 2、假如我不愿意等待很长时间，我希望过时不候，可以自动离开
         */
        String message = futureTask.get(3,TimeUnit.SECONDS);
        System.out.println(message);
        long endTime = System.currentTimeMillis();

        System.out.println("执行耗时："+(endTime - startTime) / 1000 + "秒");
    }
}
