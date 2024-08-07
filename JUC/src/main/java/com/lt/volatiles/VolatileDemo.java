package com.lt.volatiles;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class VolatileDemo {

    /**
     *不加volatile，没有可见性，程序无法停止
     * 加了volatile，保证可见性，程序可以停止
     */
    static boolean flag = true;
    //static volatile boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() +"\t--------------- come in");

            while (flag){

            }
            System.out.println(Thread.currentThread().getName() + "\tflag已被修改为：" + flag +"程序结束");
        },"t1").start();

        TimeUnit.SECONDS.sleep(2);

        flag = false;
        System.out.println(Thread.currentThread().getName()+ "\t--------------flag被修改为：" +flag);
    }
}
