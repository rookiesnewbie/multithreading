package com.lt.手写Callable与FutureTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author LiTeng
 * @Date 2023/10/1 14:08
 * Version 1.0
 * @Description 手写FutureTask
 */
public class MyFutureTask<V> implements Runnable {

    private Object lock = new Object();
    private MyCallable<V> myCallable;

    private V result;
    public MyFutureTask(MyCallable<V> myCallable) {
        this.myCallable = myCallable;
    }

    @Override
    public void run() {
        //线程要想执行的代码

        result = myCallable.call();

        //子线程执行结束唤起主线程获取结果
        synchronized (lock){
            lock.notify();
        }

    }

    public V get() throws InterruptedException {
        //获取子线程异步执行结束后的结果

        synchronized (lock){
            //让主线程阻塞
            lock.wait();
        }
        return result;
    }
}
