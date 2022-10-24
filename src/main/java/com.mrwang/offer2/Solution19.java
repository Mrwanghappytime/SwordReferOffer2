package com.mrwang.offer2;

import org.apache.commons.lang3.StringUtils;

/**
 * 正则匹配
 * . 可匹配任意一个字符，*可以匹配任意个字符
 */
public class Solution19 {
    public static void main(String[] args) {
        System.out.println(match("*c", "abc"));
    }

    public static boolean match(String regex, String source) {
        if (StringUtils.isAnyBlank(source, regex)) {
            return false;
        }
        boolean[][] matchs = new boolean[regex.length() + 1][source.length() + 1];
        matchs[0][0] = true;
        for (int i = 1; i < matchs.length; i++) {
            for (int j = 1; j < matchs[0].length; j++) {
                if (regex.charAt(i - 1) != '*') {
                    if (!matchs[i - 1][j - 1]) {
                        continue;
                    }
                    if (regex.charAt(i - 1) == '.' || regex.charAt(i - 1) == source.charAt(j - 1)) {
                        matchs[i][j] = true;
                        continue;
                    }
                } else {
                    if (matchs[i - 1][j - 1] || matchs[i][j - 1] || matchs[i - 1][j]) {
                        matchs[i][j] = true;
                        continue;
                    }
                }
            }
        }


        return matchs[regex.length()][source.length()];
    }
}
