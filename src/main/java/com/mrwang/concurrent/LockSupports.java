package com.mrwang.concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockSupports {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        Thread a = new Thread(() -> {
            for (int i = 1; i <= 100; i = i + 2) {
                System.out.println(i);
                LockSupport.unpark(thread);
                LockSupport.park();
            }
        });
        a.start();
        for (int i = 2; i <= 100; i = i + 2) {
            LockSupport.park();
            System.out.println(i);
            LockSupport.unpark(a);
        }
    }
}
