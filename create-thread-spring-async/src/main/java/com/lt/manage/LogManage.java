package com.lt.manage;

import com.lt.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author LiTeng
 * @Date 2023/9/9 13:33
 * Version 1.0
 * @Description
 */
@Component
public class LogManage {

    private static BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();
    @Value("${log.folder}")
    private  String filePath;

    public LogManage(){
        new LogThread().start();
    }

    public static void addLog(String msg){
        blockingDeque.add(msg);
    }

    class LogThread extends Thread{
        @Override
        public void run() {
            while (true){
                String log = blockingDeque.poll();

                if (!StringUtils.isEmpty(log)){
                    //将该log写入磁盘中
                    FileUtils.writeText(filePath,log,true);
                }
            }
        }
    }
}
