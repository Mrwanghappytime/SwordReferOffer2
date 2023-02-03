package com.mrwang.offer2.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
 *
 * 找出 A 中的坡的最大宽度，如果不存在，返回 0 。
 * 输入：[6,0,8,2,1,5]
 * 输出：4
 * 解释：
 * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
 */
public class Solution962 {
    public static void main(String[] args) {
        System.out.println(new Solution962().maxWidthRamp(new int[]{9,8,1,0,1,9,4,0,4,1}));
    }

    public int maxWidthRamp(int[] nums) {
        Stack<Integer> s = new Stack<>();
        int res = 0;
        int n = nums.length;
        s.push(0);
        for (int i = 0; i < n; ++i)
        {
            if (s.empty() || nums[s.peek()] > nums[i]) {
                s.push(i); // 严格单调递减栈
            }
        }
        for (int j = n - 1; j >= res; --j) // 当然你要写成j >= 0也是可以AC的
        {
            while (!s.isEmpty() && nums[s.peek()] <= nums[j])
            {
                int pos = s.peek();
                s.pop();
                res = Math.max(res, j - pos);
            }
        }
        return res;
    }
}
