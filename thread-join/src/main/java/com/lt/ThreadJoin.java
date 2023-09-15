package com.lt;

public class ThreadJoin {
    public static void main(String[] args) {
        Thread thread01 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "," + "t1");
        });

        Thread thread02 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "," + "t2");
        });

        Thread thread03 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "," + "t3");
        });

        thread03.start();
        thread01.start();
        thread02.start();
    }

    public void print(){
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+","+"t1");
        }).start();
    }
}