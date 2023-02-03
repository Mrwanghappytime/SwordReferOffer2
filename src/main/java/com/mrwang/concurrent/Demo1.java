package com.mrwang.concurrent;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @ LinkedTransferQueue 的使用
 */
public class Demo1 {
    static Integer a = 0;
    static Integer b = 2;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (a) {
                b = 3;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("aaaaaaaaaaaaaaaaaaaa");
                }
            }
        }).start();

        synchronized (b) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a) {
                System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb");
            }
        }


    }
}
