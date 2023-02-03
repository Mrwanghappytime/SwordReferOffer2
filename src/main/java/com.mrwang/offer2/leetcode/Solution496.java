package com.mrwang.offer2.leetcode;

import java.util.*;

/**
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 *
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
 *
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 *
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素
 *
 */
public class Solution496 {
    public static void main(String[] args) {

    }


    /**
     * 使用 单调栈
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> ascStack = new Stack<>();
        Map<Integer, Integer>  map = new HashMap<>();
        int result[] = new int[nums1.length];

        for (int i = nums2.length - 1; i >= 0; i--) {
            while(!ascStack.isEmpty() && ascStack.peek() < nums2[i]) {
                ascStack.pop();
            }

            map.put(nums2[i], ascStack.isEmpty() ? -1 : ascStack.peek());

            ascStack.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }
}
