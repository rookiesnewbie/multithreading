package com.lt;

public class Thread01 extends Thread {
    /**
     * 线程执行的代码就是在run方法中  执行结束 线程就死亡了
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "我是子线程");

        try {
            //让子线程阻塞3秒钟
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "子线程执行完毕");

    }

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());

        //注意：启动线程是调用start()方法，而不是run()方法
        new Thread01().start();

        new Thread01().start();

        System.out.println("主线程main执行完毕");
        //注意：调用start()线程并不是立即被cpu调度执行，而是由cpu调度执行决定
        // 调用start()方法时 线程处于就状态 ----等待cpu调度
        //
        //调用run()方法时 线程从 就绪 到运行状态

        //当run()方法 执行结束 线程就死亡了

//        子线程报错并不影响主线程的执行
    }
}