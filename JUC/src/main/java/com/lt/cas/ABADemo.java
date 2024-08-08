package com.lt.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ABADemo {

    static  AtomicReference atomicReference = new AtomicReference<Integer>(100);

    public static void main(String[] args) {
        ABADemo.ABA();
    }


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
}
