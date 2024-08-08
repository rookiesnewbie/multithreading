package com.lt.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
class User{

    private String name;

    private Integer age;
}

public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User zhangsan = new User("zhangsan", 18);
        User lisi = new User("lisi", 28);

        atomicReference.set(zhangsan);


        System.out.println(atomicReference.compareAndSet(zhangsan, lisi) + "\t" + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(zhangsan, lisi) + "\t" + atomicReference.get());


    }
}
