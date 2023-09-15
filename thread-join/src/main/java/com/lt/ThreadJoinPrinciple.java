package com.lt;

public class ThreadJoinPrinciple {

    private static Object object = new Object();
    public static void main(String[] args) {
        Thread thread = new ThreadJoinPrinciple().print();
        thread.start();

        try {
            Thread.sleep(3000);

            //中断线程
            thread.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


    public Thread print() {
            Thread thread = new Thread(() -> {
                synchronized (object){
                    System.out.println(Thread.currentThread().getName() + "----" + 1);

                    try {

                        object.wait(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "----" + 2);

                }

            });

        return thread;
    }
}