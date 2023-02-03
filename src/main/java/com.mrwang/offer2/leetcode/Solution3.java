package com.mrwang.offer2.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution3 {
    public static void main(String[] args) {
        System.out.println(new Solution3().lengthOfLongestSubstring("aabaab!bb"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        HashMap<Character, Integer> strings =  new HashMap<>(256);
        int maxLength = 1;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (strings.containsKey(s.charAt(i))) {
                maxLength = Math.max(strings.size(), maxLength);
                Integer index = strings.get(s.charAt(i));
                for (int j = start; j < index; j++) {
                    strings.remove(s.charAt(j));
                }
                start = index + 1;
            }
            strings.put(s.charAt(i), i);
        }
        return Math.max(maxLength, strings.size());
    }
}
