package com.lt;

import java.util.concurrent.TimeUnit;

public class InterruptThreadDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "线程----------isisInterrupted() = true，自己退出");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(200);
                } catch (InterruptedException e) {
                    /**
                     * sleep 方法抛出 InterruptedException后，中断标识也被清空置为 false，我们在catch 没有通过调用 th.interrupt()方法再次将中断标识置为 true，这就导致无限循环了
                     */
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }

                System.out.println("---------- hello");
            }

        }, "t1");

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t1.interrupt();

    }
}