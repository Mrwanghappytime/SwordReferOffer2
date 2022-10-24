package com.mrwang.offer2;


import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * 替换字符串中的空格为%20 --倒叙处理
 *
 */
public class Solution5 {
    public static void main(String[] args) {
        char[] chars = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', ' '};
        String s = "";
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            s += chars[random.nextInt(chars.length)];
        }
        String s1 = new String(s);
        System.out.println("begin");
        long currentTimeMillis = System.currentTimeMillis();
//        String x = ;
//        System.out.println(x);
        System.out.println(DigestUtils.md5DigestAsHex(s1.replaceAll(" ", "%20").getBytes(StandardCharsets.UTF_8)));
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("api use time:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        char[] chars1 = s.toCharArray();
        long currentTimeMillis2 = System.currentTimeMillis();
        System.out.println(getMd5OfReplaceBlack(chars1));
        long currentTimeMillis3 = System.currentTimeMillis();
        System.out.println("my use time:" + (currentTimeMillis3 - currentTimeMillis2) + "ms");

    }

    private static String getMd5OfReplaceBlack(char[] chars1) {
        int count = 0;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == ' ') {
                count++;
            }
        }
        if (count == 0) {
            return DigestUtils.md5DigestAsHex(new String(chars1).getBytes(StandardCharsets.UTF_8));
        }
        char[] char2 = new char[chars1.length + count * 2];
        int j = 0;
        for (int i = chars1.length - 1; i >= 0; i--) {
            if (chars1[i] == ' ') {
                char2[char2.length - 1 - j - 2] = '%';
                char2[char2.length - 1 - j - 1] = '2';
                char2[char2.length - 1 - j] = '0';
                j += 3;
            } else {
                char2[char2.length - 1 - j] = chars1[i];
                j = j + 1;
            }
        }
        byte[] bytes = new String(char2).getBytes(StandardCharsets.UTF_8);
        return DigestUtils.md5DigestAsHex(bytes);
    }
}
