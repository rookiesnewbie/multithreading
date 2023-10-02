package com.lt.手写线程池;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author LiTeng
 * @Date 2023/10/2 9:19
 * Version 1.0
 * @Description 队列练习
 */
public class Test {
    public static void main(String[] args) {
        //有界和无界区别。有界对容量有限制的︰无界是没有限制的      若设置成Integer .HAX_VALUE，则相当于无界
        LinkedBlockingQueue<String> objects = new LinkedBlockingQueue<>();
        objects.add("lt");
        objects.add("zhangsan");
        objects.add("lisi");

        //原则：先进先出，后进后出的原则
//        for (String object : objects) {
//            System.out.println(object);
//        }
        System.out.println(objects.poll());  //结果：lt
        System.out.println(objects.poll());  //结果：zhangsan
        System.out.println(objects.poll());  //结果：lisi
        System.out.println(objects.poll());  //结果：null
        System.out.println(objects.poll());  //结果：null
    }

}
