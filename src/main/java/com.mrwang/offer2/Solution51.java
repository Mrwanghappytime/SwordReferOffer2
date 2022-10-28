package com.mrwang.offer2;

import com.mrwang.offer2.util.ArrayUtil;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

/**
 * 求一个数组内的逆序对的总数， 对于数组a i < j a[i] > a[j], 则称i,j 为一对逆序对
 * 1. 暴力法
 * 2. 归并法
 */
public class Solution51 {
    public static void main(String[] args) throws NoSuchMethodException, ExecutionException, InterruptedException {
        int[] a = ArrayUtil.getRandomArray(1000000);
        System.out.println();
        Solution51 solution51 = new Solution51();

        Method getInversePairsOfBruteForce = solution51.getClass().getMethod("getInversePairsOfBruteForce", a.getClass());
        System.out.println((long)TakeTimeCalculate.getResult(solution51, getInversePairsOfBruteForce, a));
        Method getInversePairs = solution51.getClass().getMethod("getInversePairs", a.getClass());
        System.out.println((long)TakeTimeCalculate.getResult(solution51, getInversePairs, a));
    }

    public  long getInversePairsOfBruteForce(int[] a) {
        long count = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    count++;
                }
            }
        }
        return count;
    }


    public  long getInversePairs(int[] a) {
        int[] b = new int[a.length];
        return merge(a, 0, a.length - 1, b);
    }

    public  long merge(int[] a, int start, int end, int[] b) {
        long count = 0;
        if (end == start) {
            return 0;
        }
        count += merge(a, start, (start + end) / 2, b);
        count += merge(a, (start + end) / 2 + 1, end, b);
        int j = (start + end) / 2 + 1;
        int index = start;
        int i = start;
        for (; i <= (start + end) / 2 || j <= end;) {
            if (i > (start + end) / 2) {
                b[index++] = a[j++];
                continue;
            }
            if (j > end) {
                b[index++] = a[i++];
                continue;
            }
            if (a[i] <= a[j]) {
                b[index++] = a[i++];
                continue;
            }
            b[index++] = a[j++];
            count +=  (start + end) / 2 - i + 1;
        }
        for (int k = start; k <= end; k++) {
            a[k] = b[k];
        }
        return count;
    }
}
