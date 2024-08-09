package com.lt.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

class ClickNumber{
    int number = 0;

    public synchronized void add(){
        number++;
    }

    AtomicLong atomicLong = new AtomicLong();
    public void addByAtomicLong(){
        atomicLong.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();
    public void addByLongAdder(){
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator((left,right) -> left + right,0);
    public void addByLongAccumulator(){
        longAccumulator.accumulate(1);
    }


}

/**
 * 需求：50个线程，每个线程100w次，总点赞数出来
 */
public class AccumulatorCompareDemo {
    public static final int _1W = 10000;
    public static final int threadNumber = 50;


    public static void main(String[] args) throws InterruptedException {
        testSynchronized();
        System.out.println();

        testAtomicLong();

        System.out.println();
        testLongAdder();

        System.out.println();
        testLongAccumulator();
    }

    public static void testSynchronized() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ClickNumber clickNumber = new ClickNumber();
        CountDownLatch countDownLatch = new CountDownLatch(threadNumber);

        for (int i = 1; i <= threadNumber; i++) {

            new Thread(() ->{
                try {
                    for (int j = 1; j <= _1W * 100; j++) {
                        clickNumber.add();
                    }
                } finally {
                    countDownLatch.countDown();
                }


            }).start();

        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();

        System.out.print("通过Synchronized实现，耗时：" + (endTime-startTime) + "\t 毫秒");
        System.out.print("结果为：" + clickNumber.number);
    }

    public static void testAtomicLong() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ClickNumber clickNumber = new ClickNumber();
        CountDownLatch countDownLatch = new CountDownLatch(threadNumber);

        for (int i = 1; i <= threadNumber; i++) {

            new Thread(() ->{
                try {
                    for (int j = 1; j <= _1W * 100; j++) {
                        clickNumber.addByAtomicLong();
                    }
                } finally {
                    countDownLatch.countDown();
                }


            }).start();

        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();

        System.out.print("通过AtomicLong实现，耗时：" + (endTime-startTime) + "\t 毫秒");
        System.out.print("结果为：" + clickNumber.atomicLong.get());
    }


    public static void testLongAdder() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ClickNumber clickNumber = new ClickNumber();
        CountDownLatch countDownLatch = new CountDownLatch(threadNumber);

        for (int i = 1; i <= threadNumber; i++) {

            new Thread(() ->{
                try {
                    for (int j = 1; j <= _1W * 100; j++) {
                        clickNumber.addByLongAdder();
                    }
                } finally {
                    countDownLatch.countDown();
                }


            }).start();

        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();

        System.out.print("通过LongAdder实现，耗时：" + (endTime-startTime) + "\t 毫秒");
        System.out.print("结果为：" + clickNumber.longAdder.sum());
    }

    public static void testLongAccumulator() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ClickNumber clickNumber = new ClickNumber();
        CountDownLatch countDownLatch = new CountDownLatch(threadNumber);

        for (int i = 1; i <= threadNumber; i++) {

            new Thread(() ->{
                try {
                    for (int j = 1; j <= _1W * 100; j++) {
                        clickNumber.addByLongAccumulator();
                    }
                } finally {
                    countDownLatch.countDown();
                }


            }).start();

        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();

        System.out.print("通过LongAccumulator实现，耗时：" + (endTime-startTime) + "\t 毫秒");
        System.out.print("结果为：" + clickNumber.longAccumulator.get());
    }
}
