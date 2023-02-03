package com.mrwang.concurrent.local;

public class ThreadLocal1 {
    public static void main(String[] args) {
        while(true) {
            new Thread(() -> {
                ThreadLocal<Object> threadLocal = new ThreadLocal<>();
                threadLocal.set(new Object());
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
