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

    public synchronized void sendSMS(){
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
