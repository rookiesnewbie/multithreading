package com.lt.manage;

import com.lt.annotation.LtAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author LiTeng
 * @Date 2023/9/9 13:34
 * Version 1.0
 * @Description
 */

@Component
@Slf4j
public class OrderManage {

    /**
     * 使用异步注解，相当于开启一个新的线程
     */
    @Async
    public void asyncLog(){
        try {
            Thread.sleep(3000);

            log.info("<2>");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void asyncLog1(){
        try {
            Thread.sleep(3000);

            log.info("<2>");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 使用异步注解，并开启一个新的线程
     */
    @LtAsync
    public void asyncLog2(){
        try {
            log.info("目标方法开始执行");
            Thread.sleep(3000);

            log.info("<2>");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("目标方法执行结束");
    }

}
