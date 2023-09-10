package com.lt.wait与notify生产者与消费者模型;

/**
 * @Author LiTeng
 * @Date 2023/9/10 15:27
 * Version 1.0
 * @Description 输入线程
 */
public class InputThread extends Thread{
    private Res res;
    public InputThread(Res res){
        this.res = res;
    }
    @Override
    public void run() {
        int count = 0;
        while (true){
            synchronized (res){
                if (res.flag){
                    // 如果flag = true 则不能写入数据 只能读取数据 同时释放锁!
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (count == 0){
                    res.setUserName("顾九思");
                    res.setSex('男');

                }else {
                    res.setUserName("柳玉茹");
                    res.setSex('女');
                }
                res.flag = true;

                //唤醒输出线程 输出数据
                res.notify();
            }
            count = (count + 1) % 2;



        }
    }
}
