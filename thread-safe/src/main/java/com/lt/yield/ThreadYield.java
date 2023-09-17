package com.lt.yield;

/**
 * @Author LiTeng
 * @Date 2023/9/17 12:52
 * Version 1.0
 * @Description 主动释放cpu执行权
 */
public class ThreadYield extends Thread{

    private static int count = 50;
    public static void main(String[] args) {

        ThreadYield threadYield01 = new ThreadYield();
        ThreadYield threadYield02 = new ThreadYield();

        threadYield01.start();
        threadYield01.yield();
        threadYield02.start();

    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(Thread.currentThread().getName()+ "---->"+i);
            if (i == 30){
                System.out.println(Thread.currentThread().getName()+"主动释放cpu执行权");
                this.yield();
            }
        }
    }

}
