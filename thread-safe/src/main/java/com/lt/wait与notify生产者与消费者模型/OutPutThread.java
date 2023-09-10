package com.lt.wait与notify生产者与消费者模型;

/**
 * @Author LiTeng
 * @Date 2023/9/10 15:27
 * Version 1.0
 * @Description 输出线程
 */
public class OutPutThread extends Thread{
    private Res res;

    public OutPutThread(Res res){
        this.res = res;
    }
    @Override
    public void run() {
        while (true){
            synchronized (res){
                if (!res.flag){
                    // 如果flag = false 则不能读取数据 只能写入数据 同时释放锁!
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.getMessage();
                    }
                }

                System.out.println("名字："+res.getUserName()+","+"性别："+res.getSex());

                res.flag = false;

                //唤醒写入线程写入数据
                res.notify();

            }
        }
    }
}
