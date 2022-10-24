package com.mrwang.offer2;


import java.util.*;
import java.util.stream.Collectors;

/**
 *  题目描述: 找出数组长度为n的数组是否存在重复数字，且数字范围为1-(n-1)范围
 *  1.  要求时间复杂度为o(n)
 *  2.  要求空间复杂度为o(1) 但是需要交换数组信息
 *  3.  要求空间复杂度为o(1),不能交换数组信息
 */
public class Solution3 {
    public static void main(String[] args) {
        int[] a = new int[10000000];
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < a.length - 1; i++) {
            set.add(i);
        }
        List<Integer> integerList = set.parallelStream().collect(Collectors.toList());
        int i = 0;
        for (int b : integerList) {
            a[i++] = b;
        }
        System.out.println();
        a[a.length - 1] = random.nextInt(10000000);
        System.out.println("rand:" + a[a.length - 1]);
        for (int k = 0; k < 100; k++) {
            for (int j = 0; j < a.length / 2 - 1; j++) {
                swap(a, j, random.nextInt(a.length ));
            }
        }
        int[] newA = Arrays.copyOf(a, a.length);
        long currentTimeMillis = System.currentTimeMillis();
        Arrays.sort(a);
        int result = 0;

        for (int j = 0; j < a.length - 1; j++) {
            if (a[j] == a[j + 1]) {
                result = a[j];
                break;
            }
        }
        System.out.println("result:" + result);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("sort use time:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        int k = 0;
        while(true) {
            int[] newB = Arrays.copyOf(newA, newA.length);
            long currentTimeMillis2 = System.currentTimeMillis();
            if (k == 0) {
                System.out.println(solution2OfHash(newB));
            } else if (k == 1) {
                System.out.println(solutionOfSpaceOfO1(newB));
            } else if (k == 2) {
                System.out.println(solutionOfSpaceOfO1AndCannotSwap(newB));
            } else{
                break;
            }
            k++;
            long currentTimeMillis3 = System.currentTimeMillis();
            System.out.println("k:" + k +  ",use time:" + (currentTimeMillis3 - currentTimeMillis2) + "ms");
        }
    }

    private static void swap(int[] a, int j, int i) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }



    public static int solution2OfHash(int[] data) {
        int[] ints = new int[10000000];
        for (int i = 0; i< ints.length; i++) {
            ints[i] = -1;
        }
        for (int i = 0; i < data.length; i++) {
            if (ints[data[i]] == 1) {
                return data[i];
            } else {
                ints[data[i]] = 1;
            }
        }
        return 0;
    }

    public static int solutionOfSpaceOfO1(int[] data) {
        for (int i = 0; i < data.length;) {
            if (data[i] != i) {
                if (data[i] == data[data[i]]) {
                    System.out.println("data[i]:" + data[i] + "data[data[i]]:" + data[data[i]]);
                    return data[i];
                } else {
                    swap(data, i, data[i]);
                }
            } else {
                i++;
            }
        }
        return 0;
    }

    public static int solutionOfSpaceOfO1AndCannotSwap(int[] data) {
        return dedutionArray(data, 0, data.length);
    }

    private static int dedutionArray(int[] data, int start, int end) {
        if (start == end  || end - start == 1) {
            return start;
        }
        int meddion = (start + end) / 2;
        int count = 0;
        for (int i = 0; i <= data.length - 1; i++) {
            if (data[i] < meddion && data[i] >= start) {
                count++;
            }
        }
        return count > (meddion - start) ? dedutionArray(data, start, meddion) : dedutionArray(data, meddion, end);
    }
}
