package com.lt.synchronize;

/**
 * @Author LiTeng
 * @Date 2023/9/10 8:46
 * Version 1.0
 * @Description synchronized死锁问题
 */
public class DeadlockThread implements Runnable{

    private int count = 1;

    private final String lock = "lock";  //自定义lock锁
    @Override
    public void run() {
        while (true){
            count++;

            if (count % 2 == 0){
                // 线程1需要获取到自定义对象的 lock 在获取 a方法this锁
                // 线程2需要获取this 锁在 获取B方法 自定义对象的lock锁
                synchronized (lock){
                    a();
                }
            }else {
               synchronized (this){
                   b();
               }
            }
        }

    }

    public synchronized void a(){
        System.out.println(Thread.currentThread().getName() + "----->" + "调用a方法");
    }


    public void b(){
        synchronized (lock){
            System.out.println(Thread.currentThread().getName() + "----->" + "调用b方法");
        }

    }

    public static void main(String[] args) {
        DeadlockThread deadlockThread = new DeadlockThread();

        new Thread(deadlockThread).start();
        new Thread(deadlockThread).start();

    }

}
