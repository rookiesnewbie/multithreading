package com.lt;

public class InterruptThread extends Thread{

    private volatile boolean isStart = true;
    @Override
    public void run() {
        while (isStart){
            System.out.println(1);
//            if (this.isInterrupted()){
//                break;
//            }
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

//        interruptThread.interrupt();
            interruptThread.isStart = false;
    }
}