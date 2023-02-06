package com.mrwang.offer2.od;

import java.util.*;

public class Solution4 {

    private final static char[] WASD = new char[]{'W','A','S','D'};

    public static void main(String[] args) {

        System.out.println(new Solution4().getMinReplaceKey("AASDDDDSDDDDAAAASSSSSSSS"));

    }

    public int getMinReplaceKey(String str) {
        if (str.length() == 0 || str.length() % 4 != 0) {
            return 0;
        }

        int amount = str.length() / 4;
        Map<Character, Integer> charCountMap = getCharacterIntegerMap(str, 0, str.length());

        Set<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < WASD.length; i++) {
            if (charCountMap.getOrDefault(WASD[i], 0) > amount) {
                count += charCountMap.get(WASD[i]) - amount;
                set.add(WASD[i]);
            }
        }
        if (count == 0) {
            return 0;
        }
        int i = count;
        int matchCount = 0;
        for (; i <= str.length(); i++) {
            HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>(charCountMap);
            matchCount = calculateMatchCount(i, 0, str, set, characterIntegerHashMap, amount);
            for (int j = 1; j < str.length() - i; j++) {
                if (matchCount == count) {
                    return i;
                }

                if (set.contains(str.charAt(j - 1))) {
                    matchCount--;
                    characterIntegerHashMap.put(str.charAt(j - 1), characterIntegerHashMap.get(str.charAt(j - 1)) + 1);
                }

                if (set.contains(str.charAt(j + i))) {
                    Integer integer = characterIntegerHashMap.get(str.charAt(j + i));
                    if (integer > amount) {
                        matchCount++;
                        characterIntegerHashMap.put(str.charAt(j + i), integer - 1);
                    }
                }

                if (matchCount == count) {
                    return i;
                }

            }
        }

        return i;




    }

    private int calculateMatchCount(int length, int start, String str, Set<Character> set, Map<Character, Integer> charCountMap, int amount) {
        int result = 0;
        for (int i = start; i < start + length; i++) {
            if (set.contains(str.charAt(i))) {
                Integer integer = charCountMap.get(str.charAt(i));
                if (integer > amount) {
                    result++;
                    charCountMap.put(str.charAt(i), integer - 1);
                }
            }
        }
        return result;
    }

    private Map<Character, Integer> getCharacterIntegerMap(String str, int start, int end) {
        Map<Character, Integer> charCountMap = new HashMap<>(16);

        for (int i = start; i < end; i++) {
            charCountMap.put(str.charAt(i), charCountMap.getOrDefault(str.charAt(i), 0) + 1);
        }
        return charCountMap;
    }

}
