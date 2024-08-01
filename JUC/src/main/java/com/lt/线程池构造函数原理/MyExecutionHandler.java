package com.lt.线程池构造函数原理;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author LiTeng
 * @Date 2023/10/2 14:41
 * Version 1.0
 * @Description 自定义处理器
 */
public class MyExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自定义线程拒绝服务");
        r.run();
    }
}
