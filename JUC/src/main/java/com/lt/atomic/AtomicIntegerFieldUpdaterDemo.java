package com.lt.atomic;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class BackAccount{
    private String accountName;

    public int money1 = 0; //不适用对象的属性修改原子类

    //更新的对象属性必须使用`public volatile `修饰符
    public volatile int money2 = 0; //使用对象的属性修改原子类


    //不适用对象的属性修改原子类
    public synchronized void addMoney1(){
        money1++;
    }


    /**
     * 因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须使用静态方法newUpdater()创建一个更新器，
     * 并且需要设置想要更新的类和属性
     */
    AtomicIntegerFieldUpdater<BackAccount> fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(BackAccount.class,"money2");
    //使用对象的属性修改原子类
    public void transMoney(BackAccount backAccount){

        fieldUpdater.getAndIncrement(backAccount);
    }

}

public class AtomicIntegerFieldUpdaterDemo {

    public static final Integer SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();
    }


    /**
     * 使用synchronized的情况
     */
    public static void test1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

        BackAccount backAccount = new BackAccount();
        for (int i = 0; i < SIZE; i++) {
            new Thread(() ->{
                try {
                    for (int j = 0; j < 1000; j++) {
                        backAccount.addMoney1();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println("使用synchronized的结果为：\t" + backAccount.money1);

    }



    /**
     * 使用对象的属性修改原子类
     */
    public static void test2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

        BackAccount backAccount = new BackAccount();
        for (int i = 0; i < SIZE; i++) {
            new Thread(() ->{
                try {
                    for (int j = 0; j < 1000; j++) {
                        backAccount.transMoney(backAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println("使用对象的属性修改原子类的结果为：\t" + backAccount.money2);

    }
}
