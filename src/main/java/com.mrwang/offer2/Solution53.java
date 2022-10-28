package com.mrwang.offer2;

import com.mrwang.offer2.util.ArrayUtil;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

/**
 * 输出排序数组中元素的出现次数
 */
public class Solution53 {

    public static void main(String[] args) throws NoSuchMethodException, ExecutionException, InterruptedException {
        int[] ascArray = ArrayUtil.getAscArray(10);
        Solution53 solution53 = new Solution53();
        Method getCountOfNumberInArray = solution53.getClass().getMethod("getCountOfNumberInArray", int[].class, int.class);
        System.out.println((int)TakeTimeCalculate.getResult(solution53, getCountOfNumberInArray, ascArray, ascArray[ascArray.length - 1]));
        Method getCountOfNumberInArrayWithBinary = solution53.getClass().getMethod("getCountOfNumberInArrayWithBinary", int[].class, int.class);
        System.out.println((int)TakeTimeCalculate.getResult(solution53, getCountOfNumberInArrayWithBinary, ascArray, ascArray[ascArray.length - 1]));
    }

    public int getCountOfNumberInArray(int[] a, int number) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == number) {
                count++;
            }
            if (a[i] > number) {
                break;
            }
        }
        return count;
    }

    public int getCountOfNumberInArrayWithBinary(int[] a, int number) {
        int firstK = getFirstK(a, number, 0, a.length - 1);
        int lastK = getLastK(a, number, 0, a.length - 1);
        return firstK == -1 ? 0 : lastK - firstK + 1;

    }

    public int getFirstK(int[] a, int number, int start, int end) {
        if (start == end) {
            return a[start] == number ? start : -1;
        }
        if (a[(start + end) / 2] >= number) {
            return getFirstK(a, number, start, (start + end) / 2);
        } else {
            return getFirstK(a, number, (start + end) / 2 + 1, end);
        }
    }
    public int getLastK(int[] a, int number, int start, int end) {
        if (start == end || end < start || end - start == 1) {
            return a[start] == number ? a[end] == number ? end : start : -1;
        }
        if (a[(start + end) / 2] > number) {
            return getLastK(a, number, start, (start + end) / 2 - 1);
        } else {
            return getLastK(a, number, (start + end) / 2 , end);
        }
    }



}
