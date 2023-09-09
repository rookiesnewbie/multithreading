package com.lt.aop;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author LiTeng
 * @Date 2023/9/9 13:35
 * Version 1.0
 * @Description
 */
@Aspect
@Component
@Slf4j
public class ExtInfoThreadAop {
    /**
     * 环绕通知  可以拦截方法
     * @param joinPoint
     * @return
     */
    @Around(value = "@annotation(com.lt.annotation.LtAsync)")
    public Object around(ProceedingJoinPoint joinPoint){
        try{
            log.info("环绕通知开始执行");
            new Thread(new Runnable() {   //开启一个线程，异步执行比较耗时的方法
                @SneakyThrows
                @Override
                public void run() {
                    joinPoint.proceed();  //目标方法
                }
            }).start();

            log.info("环绕通知执行结束");
            return "环绕通知";
        }catch (Throwable throwable){
            return "系统错误";
        }
    }
}
