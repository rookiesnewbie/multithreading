package com.lt.locks;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;


//资源类，模拟3个售票员卖50张票
class Ticket{

    private int number = 50; //票数

    //ReentrantLock lock = new ReentrantLock();//非公平锁
    ReentrantLock lock = new ReentrantLock(true);//公平锁

    public void sale(){
        lock.lock();//上锁
        try{
            if (number > 0){
                System.out.println(Thread.currentThread().getName() + " 卖出第：\t" + (number--) + "\t 还剩下：" + number);

            }
        }finally {
            lock.unlock();//释放锁
        }
    }
}

public class SaleTicketDemo {
    public static void main(String[] args) {
       //SaleTicketDemo.s1();
       SaleTicketDemo.s2();
    }

    public static void s1(){
        Ticket ticket = new Ticket();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture.runAsync(() -> {
                for (int i = 0; i < 55; i++){
                    ticket.sale();
                }
            },executorService);

            CompletableFuture.runAsync(() -> {
                for (int i = 0; i < 55; i++){
                    ticket.sale();
                }
            },executorService);


            CompletableFuture.runAsync(() -> {
                for (int i = 0; i < 55; i++){
                    ticket.sale();
                }
            },executorService);
        }finally {
            executorService.shutdown();
        }
    }


    public static void s2(){
        Ticket ticket = new Ticket();
        new Thread(() ->{
            for (int i = 0; i < 55; i++){
                ticket.sale();
            }
        },"a").start();

        new Thread(() ->{
            for (int i = 0; i < 55; i++){
                ticket.sale();
            }
        },"b").start();

        new Thread(() ->{
            for (int i = 0; i < 55; i++){
                ticket.sale();
            }
        },"c").start();
    }
}
