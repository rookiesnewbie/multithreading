package com.lt.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
class User{

    private String name;

    private Integer age;
}

public class AtomicMarkableReferenceDemo {

    public static void main(String[] args) {
        User zhangsan = new User("zhangsan", 18);
        User lisi = new User("lisi", 28);
        User wang = new User("王五", 28);

        AtomicMarkableReference<User> atomicMarkableReference = new AtomicMarkableReference<>(zhangsan,false);


        new Thread(() ->{
            boolean marked = atomicMarkableReference.isMarked();

            System.out.println(Thread.currentThread().getName() + "\t 的默认标识为：" + marked);
            System.out.println(Thread.currentThread().getName() + "\t 的值为：" + atomicMarkableReference.getReference());


            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            atomicMarkableReference.compareAndSet(zhangsan,lisi,marked,!marked);
            System.out.println(Thread.currentThread().getName() + "\t 将zhangsan修改为lisi后标识为：" + atomicMarkableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + "\t 将zhangsan修改为lisi后的值为：" + atomicMarkableReference.getReference());

            atomicMarkableReference.compareAndSet(lisi,zhangsan,true,true);
            System.out.println(Thread.currentThread().getName() + "\t 将lisi又修改为zhangsan后标识为：" + atomicMarkableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + "\t 将lisi又修改为zhangsan后的值为：" + atomicMarkableReference.getReference());
        },"t1").start();





        new Thread(() ->{
            boolean marked = atomicMarkableReference.isMarked();

            System.out.println(Thread.currentThread().getName() + "\t 的默认标识为：" + marked);
            System.out.println(Thread.currentThread().getName() + "\t 的值为：" + atomicMarkableReference.getReference());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean b = atomicMarkableReference.compareAndSet(zhangsan, wang, marked, !marked);
            System.out.println(Thread.currentThread().getName() + "\t CAS的结果" + b);
            System.out.println(Thread.currentThread().getName() + "\t 将zhangsan修改为王五后标识为：" + atomicMarkableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + "\t 将zhangsan修改为王五后的值为：" + atomicMarkableReference.getReference());


            boolean c = atomicMarkableReference.compareAndSet(zhangsan, lisi, marked, !marked);
            System.out.println(Thread.currentThread().getName() + "\t CAS的结果" + c);
            System.out.println(Thread.currentThread().getName() + "\t 将zhangsan修改为lisi后标识为：" + atomicMarkableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + "\t 将zhangsan修改为lisi后的值为：" + atomicMarkableReference.getReference());
        },"t2").start();

    }
}
