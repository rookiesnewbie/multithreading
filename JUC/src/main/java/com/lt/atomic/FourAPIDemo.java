package com.lt.atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

public class FourAPIDemo {
    /**
     *
     */
    public static void main(String[] args) {
        //LongAdderAPIDemo();
        LongAccumulatorDemo();
    }

    /**
     * 只能用来计算加法，且从零开始计算
     */
    public static void LongAdderAPIDemo(){
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        long sum = longAdder.sum();
        System.out.println(sum);
    }

    /**
     * 自定义的函数操作
     */
    public static void LongAccumulatorDemo(){

        //使用匿名内部类的写法
        //LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
        //    @Override
        //    public long applyAsLong(long left, long right) {
        //        return left + right;
        //    }
        //},2);

        //使用lamb表达式的写法
        LongAccumulator longAccumulator = new LongAccumulator((left, right) -> left + right,2);
        longAccumulator.accumulate(2);
        longAccumulator.accumulate(5);
        System.out.println(longAccumulator.get());
    }
}
