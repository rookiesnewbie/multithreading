package com.lt.手写线程池;

/**
 * @Author LiTeng
 * @Date 2023/10/2 9:14
 * Version 1.0
 * @Description 手写线程池
 */

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程池核心点：复用机制
 *
 * 1．提前创建好固定的线程一直在运行状态  ---->  死循环实现
 *
 * 2．提交的线程任务缓存到一个并发队列集合中，交给我们正在运行的线程执行
 *
 * 3．正在运行的线程就从队列中获取该任务执行
 */
public class MyExecutors {

    private List<WorkThread> workThreadList;

    private BlockingQueue<Runnable> runnablesQueue;

    private boolean isRun = true;



    /**
     *
     * @param maxThreadCount  最大线程数
     * @param queueSize  线程队列大小
     */
    public MyExecutors(int maxThreadCount, int queueSize){
        //1.限制队列容量缓存
        runnablesQueue = new LinkedBlockingQueue<>(queueSize);

        //2.提前创建好线程一直处于运行状态 （即死循环）
        for (int i = 0; i < maxThreadCount; i++) {
            new WorkThread().start();
        }


    }

    public boolean execute(Runnable command){
        return runnablesQueue.offer(command);
    }


    //工作线程
    class WorkThread extends Thread{

        @Override
        public void run() {
            while (isRun || runnablesQueue.size() > 0){
                //从队列中取出线程
                Runnable runnable = runnablesQueue.poll();

                if (runnable != null){ //如果取出的队列不为null，则开启线程
                    runnable.run();
                }
            }
        }
    }


    public static void main(String[] args) {
        MyExecutors myExecutors = new MyExecutors(2, 20);
        for (int i = 0; i < 10; i++) {
            final int num = i;
            myExecutors.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "," + num);

                }
            });
        }

        myExecutors.isRun = false;


    }

}
