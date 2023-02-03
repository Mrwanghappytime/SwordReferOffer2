package com.mrwang.offer2.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 *给你一个整数数组 arr 。
 *
 * 将 arr 分割成若干 块 ，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
 *
 * 返回能将数组分成的最多块数？
 */
public class Solution768 {
    public static void main(String[] args) {
        System.out.println(new Solution768().maxChunksToSorted(new int[]{}));
    }

    public int maxChunksToSorted(int[] arr) {
        boolean[] booleans = new boolean[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            int lastPop = i;
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                if (arr[i] < arr[stack.peek()] || booleans[stack.peek()] || booleans[i]) {
                    int pop = stack.pop();
                    lastPop = arr[pop] < arr[lastPop] ? lastPop : pop;
                    booleans[i] = true;
                } else {
                    break;
                }
            }
            stack.push(lastPop);
        }
        return stack.size();
    }
}
