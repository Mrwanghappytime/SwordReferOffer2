package com.mrwang.offer2;

/**
 * 实现库函数 Math.pow();
 */
public class Solution16 {
    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();
        double power = power(1.0000000000001, 1000000000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("方法一的耗时:" + (currentTimeMillis1 - currentTimeMillis));

        long currentTimeMillis2 = System.currentTimeMillis();
        double power1 = power1(1.0000000000001, 1000000000);
        long currentTimeMillis3 = System.currentTimeMillis();
        System.out.println("方法二的耗时:" + (currentTimeMillis3 - currentTimeMillis2));
        System.out.println(power == power1);
    }

    public static double power(double base, int exponent) {
        double result = 1;
        boolean isNegtive = exponent < 0;
        exponent = isNegtive ? 0 - exponent : exponent;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return isNegtive ? 1 / result : result;
    }

    public static double power1(double base, int exponent) {
        boolean isNegtive = exponent < 0;
        exponent = isNegtive ? 0 - exponent : exponent;
        double result = powerCore(base, exponent);
        return isNegtive ? 1 / result : result;
    }

    private static double powerCore(double base, int exponent) {
        if (exponent == 1) {
            return base;
        }
        double powerCore = powerCore(base, exponent >> 1);
        return (exponent & 1) == 0 ? powerCore * powerCore : powerCore * powerCore * base;
    }
}
