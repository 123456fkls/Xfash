package com.example.xfash;

public class ThreadLocalTest {
    public static ThreadLocal<String> local = new ThreadLocal<>();
    public static void main(String[] args) {
        local.set("hello");
        System.out.println(Thread.currentThread().getName() + ":" + local.get());
        local.remove();
        System.out.println(Thread.currentThread().getName() + ":" + local.get());


    }


}
