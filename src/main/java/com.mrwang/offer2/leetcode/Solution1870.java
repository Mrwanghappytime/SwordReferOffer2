package com.mrwang.offer2.leetcode;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 给你一个浮点数 hour ，表示你到达办公室可用的总通勤时间。要到达办公室，你必须按给定次序乘坐 n 趟列车。另给你一个长度为 n 的整数数组 dist ，其中 dist[i] 表示第 i 趟列车的行驶距离（单位是千米）。
 *
 * 每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。
 *
 * 例如，第 1 趟列车需要 1.5 小时，那你必须再等待 0.5 小时，搭乘在第 2 小时发车的第 2 趟列车。
 * 返回能满足你准时到达办公室所要求全部列车的 最小正整数 时速（单位：千米每小时），如果无法准时到达，则返回 -1 。
 *
 * 生成的测试用例保证答案不超过 10^9 ，且 hour 的 小数点后最多存在两位数字 。
 *  n == dist.length
 *  1 <= n <= 105
 *  1 <= dist[i] <= 105
 *  1 <= hour <= 10^9
 * hours 中，小数点后最多存在两位数字
 *
 */

public class Solution1870 {
    public static void main(String[] args) {
        int i = 100000 * 100000;
        System.out.println(i);
        System.out.println(new Solution1870().checkSpeed(new int[] {5,3,4,6,2,2,7}, new BigDecimal(10.92).setScale(2, RoundingMode.HALF_UP), 2));
        System.out.println(new Solution1870().minSpeedOnTime(new int[] {5,3,4,6,2,2,7}, 10.92));
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        BigDecimal bigDecimal = new BigDecimal(hour).setScale(2, RoundingMode.HALF_UP);
        if (new BigDecimal(dist.length - 1).compareTo(bigDecimal) >= 0) {
            return -1;
        }

        int start = 1;
        int end = new BigDecimal(10).pow(7).intValue();
        return binaryGetMinSpeed(dist, bigDecimal, start, end);

    }

    public int binaryGetMinSpeed(int[] dist, BigDecimal hour, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            boolean use = checkSpeed(dist, hour, mid);

            if (use) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return checkSpeed(dist, hour, start) ? start : -1;
    }

    public boolean checkSpeed(int[] dist, BigDecimal hour, int speed) {
        for (int i = 0; i < dist.length - 1; i++) {
            hour = hour.subtract(new BigDecimal((dist[i] / speed) + ((dist[i] % speed) != 0 ? 1 : 0)));
            if (hour.compareTo(new BigDecimal(dist.length - 2 - i)) < 0) {
                return false;
            }
        }
        return new BigDecimal(dist[dist.length - 1]).divide(new BigDecimal(speed), 3, RoundingMode.UP).compareTo(hour) <= 0;
    }
}
