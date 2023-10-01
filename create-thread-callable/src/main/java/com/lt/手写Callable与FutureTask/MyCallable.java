package com.lt.手写Callable与FutureTask;

/**
 * @Author LiTeng
 * @Date 2023/10/1 14:03
 * Version 1.0
 * @Description 手写callable
 */
@FunctionalInterface
public interface MyCallable<V>  {
    /**
     * 当前线程 执行结束 返回的结果
     * @return
     * @throws Exception
     */
    V call();
}
