package com.mrwang.offer2;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 *  输入一个旋转数组，求数组的最小值
 */
public class Solution11 {
    public static void main(String[] args) {
        int[] a = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            a[i] = i;
        }
        int j = 0;
        Random random = new Random();
        long b[] = new long[100];
        long c[] = new long[100];
        while(j < 100) {
            int[] ints = spinArray(a, random.nextInt(500000) + 500000);
            long currentTimeMillis = System.currentTimeMillis();
            getMinValueOfForEach(ints);
            long currentTimeMillis2 = System.currentTimeMillis();
            b[j] = currentTimeMillis2 - currentTimeMillis;
            long currentTimeMillis3 = System.currentTimeMillis();
            int x = getMinValueByBinarySearch(ints);

            long currentTimeMillis4 = System.currentTimeMillis();
            c[j] = currentTimeMillis4 - currentTimeMillis3;
            j++;
        }

        long l = sumArray(b);
        long l1 = sumArray(c);
        System.out.println("on + " + l + "s");
        System.out.println("ologn + " + l1 + "s");

    }

    private static long sumArray(long[] b) {
        long sum = 0;
        for (int i = 0; i < b.length; i++) {
            sum += b[i];
        }
        return sum;
    }

    private static int getMinValueByBinarySearch(int[] ints) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < ints.length; i++) {
            min = Math.min(min, ints[i]);
        }
        return min;
    }

    private static int getMinValueOfForEach(int[] ints) {


        return binarySearchSpinArray(ints, 0, ints.length - 1);
    }

    private static int binarySearchSpinArray(int[] ints, int start, int end) {
        int anInt = ints[0];
        if (start == end || end - start == 1) {
            return Math.min(ints[start], ints[end]);
        }
        int middion = (start + end) >> 1;
        return ints[middion] > ints[start] ? binarySearchSpinArray(ints, middion, end) : binarySearchSpinArray(ints, start, middion);
    }

    private static int[] spinArray(int[] a, int index) {
        int[] b = new int[a.length];
        int j = 0;
        for (int i = index; i < a.length; i++) {
            b[j++] = a[i];
        }
        for (int i = 0; i < index; i++) {
            b[j++] = a[i];
        }
        return b;
    }

    @Test
    public void test() {
        int[] a = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            a[i] = i;
        }
        Random random = new Random();
        while(true) {
            int[] ints = spinArray(a, random.nextInt(10000000));
            System.out.println(getMinValueOfForEach(ints) == 0);
        }

    }
}
