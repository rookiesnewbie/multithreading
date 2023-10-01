package com.lt.手写Callable与FutureTask;

/**
 * @Author LiTeng
 * @Date 2023/10/1 14:44
 * Version 1.0
 * @Description
 */
public class MyCallableImpl<V> implements MyCallable {

    @Override
    public Integer call(){
        try {
            System.out.println(Thread.currentThread().getName()+"在执行耗时的代码");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 1;

    }
}
