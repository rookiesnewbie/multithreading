package com.lt;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadCallable implements Callable<Integer> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName()+"我是主线程");
        ThreadCallable threadCallable = new ThreadCallable();
        FutureTask<Integer> task = new FutureTask<Integer>(threadCallable);

        new Thread(task).start();
        Integer result = task.get();


        System.out.println("子线程返回的数据："+ result);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"我是子线程");
        Thread.sleep(3000);
        return 1;
    }
}