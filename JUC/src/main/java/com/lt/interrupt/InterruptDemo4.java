package com.lt.interrupt;



public class InterruptDemo4 {

    public static void main(String[] args){

        //测试当前线程是否被中断(检查中断标志)，返回一个boolean并清除中断状态
        // 第二次再调用时中断状态已经被清除，将返回一个false。

        System.out.println(Thread.currentThread().getName() + "\t 中断为为：" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t 中断为为：" + Thread.interrupted());

        System.out.println("=============> 1");

        Thread.currentThread().interrupt();  //中断为设置为true

        System.out.println("=============> 2");

        System.out.println(Thread.currentThread().getName() + "\t 中断为为：" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t 中断为为：" + Thread.interrupted());
    }


}
