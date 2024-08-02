package com.lt.CompletableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask = new FutureTask<>(new MyThead());
        Thread thread = new Thread(futureTask,"t1");

        thread.start();
        String message = (String) futureTask.get();
        System.out.println(message);
    }
}
class MyThead implements Callable {


    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        return "hello world";
    }
}
