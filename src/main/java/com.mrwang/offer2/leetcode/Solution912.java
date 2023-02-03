package com.mrwang.offer2.leetcode;

import java.nio.charset.StandardCharsets;

/***
 * 给你一个整数数组 nums，请你将该数组升序排列。
 */
public class Solution912 {
    public static void main(String[] args) {
        byte[] bytes = "{\"batch_id\":\"4028003785b6eeb20185bd9adf4f7afe\"}".getBytes(StandardCharsets.UTF_8);

    }

    public int[] sortArray(int nums[]) {
        return mergeSort(nums);
    }

    public int[] bubblingSort(int nums[]) {
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = i - 1; j >=0; j--) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                }
            }
        }
        return nums;
    }

    public int[] insertSort(int nums[]) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                } else {
                    break;
                }
            }
        }
        return nums;
    }

    public int[] quickSort(int nums[]) {
        int start = 0;
        int end = nums.length - 1;
        subQuickSort(nums, start, end);
        return nums;
    }

    public void subQuickSort(int nums[], int start, int end) {
        if (start >= end) {
            return;
        }
        int temp = nums[start];
        int index = start;
        int i = start, j = end;
        while (i < j) {
            while(nums[j] >= temp && i < j) {
                j--;
            }

            while (nums[i] <= temp && i < j) {
                i++;
            }

            if (i < j) {
                swap(nums, i, j);
            }
        }

        swap(nums, i , index);

        subQuickSort(nums, start, i - 1);
        subQuickSort(nums, i + 1, end);
    }

    public int[] mergeSort(int nums[]) {
        subMergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void subMergeSort(int nums[], int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start + end) >> 1;
        subMergeSort(nums, start, mid);
        subMergeSort(nums, mid + 1, end);
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int index = 0;
        while (i <= mid || j <= end) {
            if (j > end) {
                while (i <= mid) {
                    temp[index++] = nums[i++];
                }
                break;
            }

            if (i > mid) {
                while (j <= end) {
                    temp[index++] = nums[j++];
                }
                break;
            }

            if (nums[i] <= nums[j]) {
                temp[index++] = nums[i++];
            } else {
                temp[index++] = nums[j++];
            }
        }

        copyArray(nums, start, temp);

    }

    private void copyArray(int[] nums, int start, int[] temp) {
        for (int i = 0; i < temp.length; i++) {
            nums[start++] = temp[i];
        }
    }

    public void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
