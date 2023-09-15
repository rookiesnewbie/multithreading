package com.lt;

public class Main {
    public static void main(String[] args) {

        System.out.println("主线程开始执行");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                        System.out.println("我是子线程");

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }


            }
        });

        thread.start();


        System.out.println("主线程执行结束");


    }
}