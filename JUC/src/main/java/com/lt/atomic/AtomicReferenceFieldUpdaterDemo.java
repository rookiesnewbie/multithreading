package com.lt.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 需求:
 * 多线程并发调用一个类的初始化方法，如果未被初始化过，将执行初始化工作，
 * 要求只能被初始化一次，只有一个线程操作成功
 */


class MyVar{
    public volatile  Boolean isInit = Boolean.FALSE;

    AtomicReferenceFieldUpdater<MyVar,Boolean> fieldUpdater =
            /**
             * 参数一：类
             * 参数二：类中要修改的字段类型
             * 参数三：类中要修改的字段名
             */
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class,Boolean.class,"isInit");

    public void init(MyVar myVar) throws InterruptedException {
        if (fieldUpdater.compareAndSet(myVar,Boolean.FALSE,Boolean.TRUE)){
            System.out.println(Thread.currentThread().getName() + "\t 正在 init ，please wait 3 seconds");

            TimeUnit.SECONDS.sleep(3);

            System.out.println(Thread.currentThread().getName() + "\t  init finish");
        }else {
            System.out.println(Thread.currentThread().getName() + "已有其它的线程完成初始化工作，你无需再次初始化");
        }
    }
}

public class AtomicReferenceFieldUpdaterDemo {

    public static void main(String[] args) {

        MyVar myVar = new MyVar();

        for (int i = 0; i < 5; i++) {
            new Thread(() ->{
                try {
                    myVar.init(myVar);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }
    }
}
