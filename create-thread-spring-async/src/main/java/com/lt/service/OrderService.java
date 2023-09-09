package com.lt.service;

import com.lt.manage.OrderManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author LiTeng
 * @Date 2023/9/9 13:31
 * Version 1.0
 * @Description 订单业务
 */
@RestController
@Slf4j
public class OrderService {

    @Resource
    private OrderManage orderManage;

    /**
     * 使用异步注解实现
     * @return
     */
    @GetMapping("/addOrder")
    public String addOrder(){
        log.info("<1>");

        orderManage.asyncLog();
        log.info("<3>");

        return "3";
    }


    /**
     * 不使用异步注解实现
     * @return
     */
    @GetMapping("/order")
    public String getOrder(){
        log.info("<1>");

        new Thread(new Runnable() {
            @Override
            public void run() {
                orderManage.asyncLog1();
            }
        }).start();
        log.info("<3>");

        return "3";
    }

    /**
     * 不使用多线程异步处理
     * @return
     */
    @GetMapping("/1")
    public String Order(){
        log.info("<1>");

        orderManage.asyncLog1();
        log.info("<3>");

        return "3";
    }


    /**
     * 使用自定义异步注解实现
     * @return
     */
    @GetMapping("/get")
    public String get(){
        log.info("<1>");

        orderManage.asyncLog2();
        log.info("<3>");

        return "3";
    }
}
