package com.mrwang.offer2.od;

import java.util.Arrays;

public class Solution7 {
    public static void main(String[] args) {
        System.out.println(new Solution7().getNeedBicycle(3, 4, new int[]{3, 2, 2, 1}));
    }

    public int getNeedBicycle(int m, int n, int[] heights) {
        int result = 0;
        Arrays.sort(heights);
        int i = 0, j = n - 1;
        while(i < j) {
            if (heights[j] + heights[i] <= m) {
                result++;
                j--;
                i++;
            } else {
                result++;
                j--;
            }
        }
        if (i == j) {
            result++;
        }
        return result;
    }
}
