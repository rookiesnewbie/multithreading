package com.lt.volatiles;

import java.util.concurrent.TimeUnit;

class myNumber{
    //int number;
    //public synchronized void addNumber(){
    //    number++;
    //}

    volatile int number;
    public  void addNumber(){
        number++;
    }
}
public class VolatileNoAtomicDemo {
    public static void main(String[] args) {

        myNumber myNumber = new myNumber();

        for (int i = 1; i <= 10; i++){
            new Thread(() ->{
                for (int j = 1; j <= 1000; j++){
                    myNumber.addNumber();
                }
            },String.valueOf(i)).start();
            myNumber.addNumber();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(myNumber.number);
    }
}
