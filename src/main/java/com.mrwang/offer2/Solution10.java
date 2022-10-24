package com.mrwang.offer2;

public class Solution10 {
    private static int a[] = new int[1000];
    public static void main(String[] args) {

        System.out.println("8ac683d66e8d0eef026e8e4888030044".hashCode() % 128);
//        System.out.print(feebonaqie(99) + " ");

    }

    private static int feebonaqie(int i) {
        if (i <= 2) {
            return i;
        }
        int one = 0;
        int two = 1;
        int result = 0;
        for(int k = 2; k <= i; k++) {
            result = one + two;
            one = two;
            two = result;
        }
        return result;
    }
}
