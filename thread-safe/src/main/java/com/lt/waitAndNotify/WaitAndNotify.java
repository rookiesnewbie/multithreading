package com.lt.waitAndNotify;

/**
 * @Author LiTeng
 * @Date 2023/9/10 11:48
 * Version 1.0
 * @Description
 */
public class WaitAndNotify {

    private Object objectLock = new Object();
    private String lock = "lock";

    public static void main(String[] args){

        new WaitAndNotify().print();

    }

    public  void print() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * this.wait();释放资源 同时当前线程会阻塞
                 */

                synchronized(lock){
                    System.out.println(">1<");

                    //获取到锁的对象.wait()
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(">2<");
                }
            }
        }).start();

        try {
            //主线程3s后唤醒子线程
            Thread.sleep(3000);
            synchronized (lock){
                lock.notify();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
