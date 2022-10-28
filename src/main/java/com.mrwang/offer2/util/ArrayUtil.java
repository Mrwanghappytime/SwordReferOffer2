package com.mrwang.offer2.util;

import java.util.Random;

public class ArrayUtil {
    public static int[] getRandomArray(int count) {
        int[] a = new int[count];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100000);
        }
        return a;
    }

    public static int[] getAscArray(int count) {
        int[] a = new int[count];
        Random random = new Random();
        int value = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] = value + random.nextInt(2);
            value = a[i];
        }
        return a;
    }

    public static void main(String[] args) {
        int[] ascArray = getAscArray(10);
    }
}
