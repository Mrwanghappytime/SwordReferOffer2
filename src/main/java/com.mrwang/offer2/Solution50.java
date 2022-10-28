package com.mrwang.offer2;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 字符串中第一次只出现一次的字符
 */
public class Solution50 {
    public static void main(String[] args) {
        String s = RandomStringUtils.random( 100, true, true);
        System.out.println(s.toLowerCase());
        System.out.println(getFirstSingleChar(s.toLowerCase()));
    }

    public static char getFirstSingleChar(String str) {
        int[] hash = new int[256];
        for (int i = 0; i < str.length(); i++) {
            hash[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (hash[str.charAt(i)] == 1) {
                return str.charAt(i);
            }
        }
        return 0;
    }
}
