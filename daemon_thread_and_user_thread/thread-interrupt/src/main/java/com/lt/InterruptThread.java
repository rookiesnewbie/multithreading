package com.lt;

public class InterruptThread extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println(1);
           /* try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(2);
        }
    }

    public static void main(String[] args) {
        InterruptThread interruptThread = new InterruptThread();
        interruptThread.start();
        System.out.println("主线程");

        try {
            Thread.sleep(3000);
            System.out.println("子线程333");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        interruptThread.interrupt();
    }
}