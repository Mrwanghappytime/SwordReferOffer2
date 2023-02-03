package com.mrwang.concurrent;

import java.util.concurrent.TimeUnit;

public class ThreadState {
    public static void main(String[] args) {
        Object o = new Object();
        new Thread(() -> {


            synchronized (o) {
                try {
                    System.out.println("线程1进入成功");
                    o.wait();
                    System.out.println("线程1执行成功");
                } catch (InterruptedException e) {

                }
            }
        }).start();
        new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println("线程2进入成功");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                }
                try {
                    System.out.println("线程2执行成功");
                    o.notify();
                } catch (Exception e) {

                }
            }
        }).start();

    }
}
