package com.lt.cas;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 题目:实现一个自旋锁，复习CAS思想
 * 自旋锁好处:循坏比较获取没有类似wait的阻塞
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用mvLock方法自己持有锁5秒钟，B随后进来后发现当前有线程持有锁，
 * 所以只能通过自旋等待，直到A释放锁后B随后抢到。
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<Thread>();

    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t==================> come in ");

        while (!atomicReference.compareAndSet(null,thread)){

        }

    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName() + "\t==================> task over ");
    }

    public static void main(String[] args) throws InterruptedException {

        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() ->{
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            spinLockDemo.unlock();
        },"t1").start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(() ->{
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            spinLockDemo.unlock();
        },"t2").start();
    }
}
