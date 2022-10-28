package com.mrwang.offer2;

/**
 *  求第N个丑数， 只包含2,3,5因子的数成为丑数
 *  1 为第一个丑数
 */
public class Solution49 {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(getUgly(i));
        }
    }

    public static int getUgly(int index) {
        if (index == 1) {
            return 1;
        }
        int ugly[] = new int[index];
        ugly[0] = 1;
        int t2Index = 0;
        int t3Index = 0;
        int t5Index = 0;
        for (int i = 1; i < index; i++) {
            int j = 0;
            for (j = t2Index; j < i; j++) {
                if (ugly[j] * 2 <= ugly[i - 1]) {
                    continue;
                }
                break;
            }
            t2Index = j;
            for (j = t3Index; j < i; j++) {
                if (ugly[j] * 3 <= ugly[i - 1]) {
                    continue;
                }
                break;
            }
            t3Index = j;

            for (j = t5Index; j < i; j++) {
                if (ugly[j] * 5 <= ugly[i - 1]) {
                    continue;
                }
                break;
            }
            t5Index = j;
            ugly[i] = Math.min(Math.min(ugly[t5Index] * 5, ugly[t3Index] * 3), ugly[t2Index] * 2);

        }
        return ugly[index - 1];
    }
}
