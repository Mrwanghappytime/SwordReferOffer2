package com.mrwang.concurrent;

public class SharedMemoryDemo {
    private static int count = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 10000) {
                if (count % 3 == 0) {
                    count++;
                    System.out.println(count);
                }
            }

        }).start();
        new Thread(() -> {
            while (count < 10000) {
                if (count % 3 == 1) {
                    count++;
                    System.out.println(count);
                }
            }

        }).start();
        new Thread(() -> {
            while (count < 10000) {
                if (count % 3 == 2) {
                    count++;
                    System.out.println(count);
                }
            }

        }).start();
    }
}
