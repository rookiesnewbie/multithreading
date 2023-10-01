package com.lt.手写Callable与FutureTask;

/**
 * @Author LiTeng
 * @Date 2023/10/1 14:49
 * Version 1.0
 * @Description
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        MyCallableImpl<Integer> myCallable = new MyCallableImpl<>();
        MyFutureTask<Integer> futureTask = new MyFutureTask<>(myCallable);
        new Thread(futureTask).start();
        Integer reslut = futureTask.get();

        System.out.println(Thread.currentThread().getName()+":"+ reslut);
    }

}
