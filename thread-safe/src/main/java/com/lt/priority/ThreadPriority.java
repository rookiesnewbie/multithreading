package com.lt.priority;

/**
 * @Author LiTeng
 * @Date 2023/9/17 13:20
 * Version 1.0
 * @Description 线程优先级
 */
public class ThreadPriority extends Object{


    public static void main(String[] args) {

        ThreadPriority threadPriorty01 = new ThreadPriority();
        ThreadPriority threadPriorty02 = new ThreadPriority();

        threadPriorty01.print(Thread.MIN_PRIORITY);
        threadPriorty02.print(Thread.MAX_PRIORITY);

    }

    public void print(int priority){
        Thread thread = new Thread(() -> {
            int count = 5;
            while (true) {
                count++;
                System.out.println(Thread.currentThread().getName() + "---->" + count);
            }
        });

        //设置线程的优先级
        thread.setPriority(priority);

        //开启线程
        thread.start();
    }
}
