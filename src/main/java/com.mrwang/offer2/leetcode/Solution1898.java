package com.mrwang.offer2.leetcode;

import java.util.*;

/**
 * "abcacb"
 * "ab"
 * [3,1,0]
 */
public class Solution1898 {
    public static void main(String[] args) {
        System.out.println(new Solution1898().maximumRemovals("abcacb", "ab", new int[]{3,1,0}));
    }

    public int maximumRemovals(String s, String p, int[] removable) {
        if (!isSubString(s, p , removable, 1)) {
            return 0;
        }
        int left = 1;
        int right = removable.length;
        while (left < right) {
            int mid = ((left + right) >> 1) + ((left + right) & 1);
            if (isSubString(s, p, removable, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    boolean isSubString(String s, String p, int[] removable, int mid) {
        boolean removeFlag[] = new boolean[s.length()];

        for (int i = 0; i < mid; i++) {
            removeFlag[removable[i]] = true;
        }
        int start = 0;
        for (int i = 0; i < p.length();) {
            int index = s.indexOf(p.charAt(i), start);
            if (index == -1) {
                return false;
            }
            if (!removeFlag[index]) {
                i++;
            }
            start = index + 1;
        }
        return true;
    }

}
