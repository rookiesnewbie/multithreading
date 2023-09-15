package com.lt;

public class ThreadJoin {
    public static void main(String[] args) {
        Thread thread01 = new Thread(() -> {
            {
                System.out.println(Thread.currentThread().getName() + "," + "t1");
            }
        });

        Thread thread02 = new Thread(() -> {
            {
                try {
                    thread01.join();  //让线程t2在线程t1执行完后执行
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "," + "t2");
            }
        });

        Thread thread03 = new Thread(() -> {
            {
                try {
                    thread02.join();  //让线程t3在线程t2执行完后执行
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "," + "t3");

            }
        });

        thread01.start();
        thread02.start();
        thread03.start();
       /* ThreadJoin thread01 = new ThreadJoin();
        ThreadJoin thread02 = new ThreadJoin();
        ThreadJoin thread03 = new ThreadJoin();

        thread03.print("t3");
        thread01.print("t1");
        thread02.print("t2");*/
    }

    public void print(String thread){
        new Thread(() ->{

            System.out.println(Thread.currentThread().getName()+","+thread);
        }).start();
    }
}