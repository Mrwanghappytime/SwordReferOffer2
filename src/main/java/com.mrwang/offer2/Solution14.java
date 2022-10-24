package com.mrwang.offer2;

import java.math.BigDecimal;

/**
 *  一段长度为n的绳子剪为m段，求每段长度乘机最大值
 */
public class Solution14 {
    public static void main(String[] args) {
        System.out.println(getSegmentMaxMulti(6));
    }

    public static int getSegmentMaxMulti(int length) {
        if (length < 2) {
            return length;
        }
        int x = length / 3;
        int i = length % 3;
        if (i == 1) {
            return new BigDecimal(3).pow(x - 1).intValue() * 4;
        }
        return new BigDecimal(3).pow(x).intValue() * (i > 0 ? i : 1);

    }


}
