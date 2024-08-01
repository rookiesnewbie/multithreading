package com.lt.ThreadLocal;

/**
 * @Author LiTeng
 * @Date 2023/10/3 16:23
 * Version 1.0
 * @Description
 */
public class ThreadLocalDeom {
    public static void main(String[] args) {
        final ThreadLocal<String> threadLocal = new ThreadLocal<>();
//        threadLocal.set("zhangsan");
//        threadLocal.set("lisi");  //后者会覆盖前者
//
//        threadLocal.remove();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("zhangsan");
                System.out.println("子线程："+threadLocal.get());
            }
        }).start();

        System.out.println("主线程："+threadLocal.get());
    }

}
