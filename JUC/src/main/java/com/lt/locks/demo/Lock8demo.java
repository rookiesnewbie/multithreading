package com.lt.locks.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendEmail(){
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("---------------sendEmail");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public  synchronized void sendSMS(){
        System.out.println("---------------sendSMS");
    }

    public  void hello(){
        System.out.println("---------------hello");
    }
}

public class Lock8demo {

    public static void main(String[] args) {
        //Lock8demo.dmeo1();  //先打印sendEmail后打印sendSMS
        //Lock8demo.dmeo2();  //先打印sendEmail后打印sendSMS
        //Lock8demo.dmeo3();  //先打印hello后打印sendEmail
        //Lock8demo.dmeo4();  //先打印sendSMS后打印sendEmail
        //Lock8demo.dmeo5();  //先打印sendEmail后打印sendSMS
        //Lock8demo.dmeo6();  //先打印sendEmail后打印sendSMS
        //Lock8demo.dmeo7();  //先打印sendSMS后打印sendEmail
        Lock8demo.dmeo8();  //先打印sendSMS后打印sendEmail

        /**
         * 总结：
         * 1-2：
         *      一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了,则其它的线程都只能等待，
         *      换句话说，某一个时刻内，只能有唯一的一个线程去访问
         *      synchronized方法锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
         *
         * 3-4：
         *      加个普通方法后发现和同步锁无关
         *      换成两个对象后，不是同一把锁了，情况立刻变化。
         *
         * 5-6：
         *      都换成静态同步方法后，情况又变化
         *      三种 synchronized 锁的内容有一些差别:
         *          对于普通同步方法，锁的是当前实例对象，通常指this,具体的一部部手机,所有的普通同步方法用的都是同一把锁一>实例对象本身,
         *          对于静态同步方法，锁的是当前类的class对象，如Phone.class唯一的一个模板
         *          对于同步方法块，锁的是synchronized括号内的对象
         *
         * 7-8：
         *      当一个线程试图访问同步代码时它首先必须得到锁，正常退出或抛出异常时必须释放锁。
         *      所有的普通同步方法用的都是同一把锁一实例对象本身，就是new出来的具体实例对象本身,本类this
         *      也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他普通同步方法必须等待获取锁的方法释放锁后才能获取锁。
         *
         *      所有的静态同步方法用的也是同一把锁-类对象本身，就是我们说过的唯一模板class
         *      具体实例对象this和唯一模板class，这两把锁是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竟态条件的但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁。
         */
    }

    /**
     * 标准访问，有2个线程，请问先打印发邮件还是先打印发短信？
     *
     * 先打印sendEmail后打印sendSMS
     */
    public static void dmeo1(){
        Phone phone = new Phone();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try{
            CompletableFuture.runAsync(() ->{
                phone.sendEmail();
            },threadPool);

            TimeUnit.SECONDS.sleep(2);

            CompletableFuture.runAsync(() ->{
                phone.sendSMS();
            },threadPool);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }


    /**
     * sendEmail方法加入暂停3秒，有2个线程，请问先打印发邮件还是先打印发短信？
     *
     * 先打印sendEmail后打印sendSMS
     */
    public static void dmeo2(){
        Phone phone = new Phone();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try{
            CompletableFuture.runAsync(() ->{
                phone.sendEmail();
            },threadPool);

            TimeUnit.SECONDS.sleep(2);

            CompletableFuture.runAsync(() ->{
                phone.sendSMS();
            },threadPool);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }



    /**
     *  添加一个普通的hello方法，请问先打印邮件还是hello？
     *
     *
     *  先打印hello后打印sendEmail
     */
    public static void dmeo3(){
        Phone phone = new Phone();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try{
            CompletableFuture.runAsync(() ->{
                phone.sendEmail();
            },threadPool);

            TimeUnit.SECONDS.sleep(2);

            CompletableFuture.runAsync(() ->{
                phone.hello();
            },threadPool);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    /**
     * 有两部手机，，请问先打印发邮件还是先打印发短信？
     *
     * 先打印sendSMS后打印sendEmail
     */
    public static void dmeo4(){
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try{
            CompletableFuture.runAsync(() ->{
                phone1.sendEmail();
            },threadPool);

            TimeUnit.SECONDS.sleep(2);

            CompletableFuture.runAsync(() ->{
                phone2.sendSMS();
            },threadPool);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    /**
     * 有两个静态同步方法，有1部手机，，请问先打印发邮件还是先打印发短信？
     *
     * 先打印sendEmail后打印sendSMS
     */
    public static void dmeo5(){
        Phone phone = new Phone();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try{
            CompletableFuture.runAsync(() ->{
                phone.sendEmail();
            },threadPool);

            TimeUnit.SECONDS.sleep(2);

            CompletableFuture.runAsync(() ->{
                phone.sendSMS();
            },threadPool);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }


    /**
     * 有两个静态同步方法，有2部手机，，请问先打印发邮件还是先打印发短信？
     *
     * 先打印sendEmail后打印sendSMS
     */
    public static void dmeo6(){
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try{
            CompletableFuture.runAsync(() ->{
                phone1.sendEmail();
            },threadPool);

            TimeUnit.SECONDS.sleep(2);

            CompletableFuture.runAsync(() ->{
                phone2.sendSMS();
            },threadPool);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }


    /**
     * 有1个静态同步方法,1个普通同步方法，有1部手机，，请问先打印发邮件还是先打印发短信？
     *
     * 先打印sendSMS后打印sendEmail
     */
    public static void dmeo7(){
        Phone phone = new Phone();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try{
            CompletableFuture.runAsync(() ->{
                phone.sendEmail();
            },threadPool);

            TimeUnit.SECONDS.sleep(2);

            CompletableFuture.runAsync(() ->{
                phone.sendSMS();
            },threadPool);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    /**
     * 有1个静态同步方法,1个普通同步方法，有2部手机，，请问先打印发邮件还是先打印发短信？
     *
     * 先打印sendSMS后打印sendEmail
     */
    public static void dmeo8(){
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try{
            CompletableFuture.runAsync(() ->{
                phone1.sendEmail();
            },threadPool);

            TimeUnit.SECONDS.sleep(2);

            CompletableFuture.runAsync(() ->{
                phone2.sendSMS();
            },threadPool);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }



}
