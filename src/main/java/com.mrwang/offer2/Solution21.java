package com.mrwang.offer2;

import java.util.*;

/**
 * 调整数组的顺序，使得数组的奇数部分都在偶数前面
 * 1. 暴力法 -- 取出偶数，所有数字后移，将数字放到最后
 * 2. 原地等待交换法
 */
public class Solution21 {

    private final static Map<Integer, NeedSwapInterface> needSwapInterfaceMap;

    static {
        needSwapInterfaceMap = new HashMap<>();
        needSwapInterfaceMap.put(NeedSwapType.PARITY.getType(), new ParityNeedSwap());
    }


    public static void main(String[] args) {
        for (int j = 0; j < 100; j++) {
            int[] a = new int[100000];

            Random random = new Random();
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(100000);
            }
            long currentTimeMillis = System.currentTimeMillis();
            int[] ints = customSortOfBruteForce(a);
            long currentTimeMillis1 = System.currentTimeMillis();
            System.out.println("use time0 " + (currentTimeMillis1 - currentTimeMillis));
            long currentTimeMillis2 = System.currentTimeMillis();
            int[] ints1 = customSortOfCircle(a);
            long currentTimeMillis3 = System.currentTimeMillis();
            System.out.println("use time1 " + (currentTimeMillis3 - currentTimeMillis2));

            System.out.println("+++++++++++++++++++++++++++++++++++");


        }
    }

    private static void printf(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println();
    }

    public static int[] customSortOfBruteForce(int[] a) {
        int[] ints = Arrays.copyOf(a, a.length);
        int end = a.length - 1;
        for (int i = 0; i < end;) {
            if ((ints[i] & 1) == 0) {
                for (int j = i; j < end; j++) {
                    swap(ints, j, j + 1);
                }
                end = end - 1;
            } else {
                i++;
            }
        }
        return ints;
    }


    public static int[] customSortOfCircle(int[] a) {
        int[] ints = Arrays.copyOf(a, a.length);
        int end = a.length - 1;
        for (int i = 0; i <= end; i++) {
            if (needSwap(ints[i], NeedSwapType.PARITY.getType())) {
                for (int j = end; j > i; j--) {
                    if ((ints[j] & 1) == 1) {
                        swap(ints, i, j);
                        end = j;
                        break;
                    }
                }
            }
        }
        return ints;
    }

    private static boolean needSwap(int anInt, Integer type) {
        NeedSwapInterface needSwapInterface = needSwapInterfaceMap.get(type);
        if (needSwapInterface == null) {
            throw new RuntimeException("不存在支持的规则");
        }
        return needSwapInterface.needSwap(anInt);
    }

    public static void swap(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }
}

interface NeedSwapInterface {
    boolean needSwap(int value);
}

class ParityNeedSwap implements NeedSwapInterface {

    @Override
    public boolean needSwap(int value) {
        return (value & 1) == 0;
    }
}

enum NeedSwapType {

    PARITY(0, "奇偶性");

    private Integer type;
    private String title;

    NeedSwapType(Integer type, String title) {
        this.type = type;
        this.title = title;
    }

    public Integer getType() {
        return type;
    }
}
