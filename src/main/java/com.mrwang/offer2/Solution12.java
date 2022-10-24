package com.mrwang.offer2;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 问题12 回溯解决矩阵是否存在包含某个字符的路径，每个格子只能走一次
 */
public class Solution12 {
    private static String end = "";
    public static void main(String[] args) {
        char[][] chars = new char[50][50];
        Random random = new Random();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                chars[i][j] = (char) (random.nextInt(26) + (int)'a');
                System.out.print(chars[i][j] + " ");
            }
            System.out.println();
        }
        int j = 0;
        while(j < 10) {
            String path = RandomStringUtils.random(4, true, false).toLowerCase();
            boolean b = containPath(chars, path);
            System.out.println("contain path:" + path + ",result:" + b + ",location:" + end);
            j++;
        }
    }

    // 回溯解题模板 从初始条件出发，到最后一个满足或者不满足条件中止，如果中止，判断状态，不满足则回溯，需要还原状态到上一次开始判断的时间

    public static boolean containPath(char[][] chars, String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        if (chars == null) {
            return false;
        }
        boolean[][] booleans = new boolean[chars.length][chars[0].length];
        for (int i = 0; i <  chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                if (containPathCore(chars, str, i, j, 0, booleans)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean containPathCore(char[][] chars, String str, int i, int j, int index, boolean[][] booleans) {
        if (index == str.length()) {
            end = (i + 1) + "*" + j;
            return true;
        }
        if (i < 0 || j < 0 || i >= chars.length || j >= chars[0].length ||  booleans[i][j] || chars[i][j] != str.charAt(index)) {
            return false;
        }
        booleans[i][j] = true;
        boolean b = containPathCore(chars, str, i - 1, j, index + 1, booleans) ||
                containPathCore(chars, str, i + 1, j, index + 1, booleans) ||
                containPathCore(chars, str, i, j - 1, index + 1, booleans) ||
                containPathCore(chars, str, i, j + 1, index + 1, booleans);
        if (!b) {
            end = "";
            booleans[i][j] = false;
        }
        return b;
    }

}
