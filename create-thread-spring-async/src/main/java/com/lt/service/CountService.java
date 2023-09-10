package com.lt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LiTeng
 * @Date 2023/9/10 11:04
 * Version 1.0
 * @Description
 */

@RestController
@Slf4j
@Scope(value = "prototype")
public class CountService {
    private int count = 1;

    @GetMapping("/count")
    public synchronized Integer count(){
        try {
            log.info(">{}<",count++);
            try {
                Thread.sleep(3000);
                count++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }catch (Exception e){

        }
        return count;
    }

}
