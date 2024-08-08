package com.lt.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static  AtomicReference atomicReference = new AtomicReference<Integer>(100);
    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference<Integer>(100,1);

    public static void main(String[] args) {
        //ABADemo.ABA();
        ABADemo.resolveABA();
    }


    /**
     * 发生ABA问题
     */
    public static void ABA(){
        new Thread(() -> {

            atomicReference.compareAndSet(100,101);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            atomicReference.compareAndSet(101,100);

        },"t1").start();


        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean b = atomicReference.compareAndSet(100, 2024);


            System.out.println(Thread.currentThread().getName() + "\t" + b + "\t "+ atomicReference.get());

        },"t2").start();
    }

    /**
     * 解决ABA问题
     */
    public static void resolveABA(){
        new Thread(() -> {
            //获取流水版本号
            int stamp = atomicStampedReference.getStamp();

            System.out.println(Thread.currentThread().getName() + "\t首次流水版本号为：\t" + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            atomicStampedReference.compareAndSet(100,101,stamp,stamp+1);

            System.out.println(Thread.currentThread().getName() + "\t 2次流水版本号为：\t" + atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);

            System.out.println(Thread.currentThread().getName() + "\t 2次流水版本号为：\t" + atomicStampedReference.getStamp());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            atomicReference.compareAndSet(101,100);

        },"t3").start();


        new Thread(() -> {
            //获取流水版本号
            int stamp = atomicStampedReference.getStamp();

            System.out.println(Thread.currentThread().getName() + "\t首次流水版本号为：\t" + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean b = atomicStampedReference.compareAndSet(100, 2024, stamp, stamp + 1);


            System.out.println(Thread.currentThread().getName() + "\t" + b + "\t "+ atomicStampedReference.getReference() + " \t" + atomicStampedReference.getStamp());

        },"t4").start();
    }
}
