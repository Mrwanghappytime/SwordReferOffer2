package com.mrwang.concurrent;

public class ThreadLocalOfSubThread {
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("11111");
        System.out.println(threadLocal.get());
        new Thread(() -> {
            System.out.println("子线程" + threadLocal.get());
        }).start();
    }
}
