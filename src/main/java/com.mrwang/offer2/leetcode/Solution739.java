package com.mrwang.offer2.leetcode;

import java.util.*;

/***
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * temperatures = [73,74,75,71,69,72,76,73]
 * [1,1,4,2,1,1,0,0]
 */
public class Solution739 {

    public static void main(String[] args) {
        Solution739 solution739 = new Solution739();
        int[] ints = solution739.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
    }

    public int[] dailyTemperatures(int[] temperatures) {
        return dailyTemperatures2(temperatures);
    }

    public int[] dailyTemperatures1(int[] temperatures) {
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length - 1; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    public int[] dailyTemperatures2(int[] temperatures) {
        Stack<Integer> ascStack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!ascStack.isEmpty() && ascStack.peek() <= temperatures[i]) {
                Integer pop = ascStack.pop();
                Integer integer = map.get(pop);
                res[i] += integer != null ? integer : 0;
            }

            if (ascStack.isEmpty()) {
                res[i] = 0;
            } else {
                res[i] += 1;
            }
            ascStack.push(temperatures[i]);
            map.put(temperatures[i], res[i]);
        }

        return res;
    }

    public int[] dailyTemperatures3(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }
}
