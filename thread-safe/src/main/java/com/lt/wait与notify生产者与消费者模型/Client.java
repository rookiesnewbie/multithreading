package com.lt.wait与notify生产者与消费者模型;

/**
 * @Author LiTeng
 * @Date 2023/9/10 15:42
 * Version 1.0
 * @Description 客户端
 */
public class Client {
    public static void main(String[] args) {
        new Client().print();
    }

    public void print(){
        Res res = new Res();
        InputThread inputThread = new InputThread(res);
        OutPutThread outPutThread = new OutPutThread(res);
        inputThread.start();
        outPutThread.start();
    }

}
