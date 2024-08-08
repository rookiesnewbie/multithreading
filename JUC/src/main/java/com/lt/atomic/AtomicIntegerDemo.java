package com.lt.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class MyNumber{
    AtomicInteger atomicInteger = new AtomicInteger();

    public void increment(){
        atomicInteger.incrementAndGet();
    }
}

public class AtomicIntegerDemo {

    public static final Integer SIZE = 50;

    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();

        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for (Integer i = 0; i < SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        myNumber.increment();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t result:" + myNumber.atomicInteger.get());
    }

}
