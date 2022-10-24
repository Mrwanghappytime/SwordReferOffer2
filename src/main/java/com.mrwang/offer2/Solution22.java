package com.mrwang.offer2;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 *  获取链表的倒数第K个节点
 *  1. 第一遍循环，计数，总数为n，第二遍遍历到n-k+1个节点结束
 *  2. 双指针
 */

public class Solution22 {

    public static void main(String[] args) throws NoSuchMethodException {
        Solution22 solution22 = new Solution22();
        Method findKthTiTail1 = Solution22.class.getMethod("findKthTiTail1", Node.class, Integer.class);
        Method findKthTiTail2 = Solution22.class.getMethod("findKthTiTail2", Node.class, Integer.class);
        Node head = generateLinkedList(1000000);
        Random random = new Random();
        int i = random.nextInt( 1000000 - 1);
        TakeTimeCalculate.submit(solution22, findKthTiTail1, head, 10);
        TakeTimeCalculate.submit(solution22, findKthTiTail2, head, 10);
    }

    private static Node generateLinkedList(int count) {
        Random random = new Random();
        Node head = new Node();
        head.value = random.nextInt(Integer.MAX_VALUE - 1);
        Node iterator = head;
        while (count > 1) {
            Node node = new Node();
            node.value = random.nextInt(Integer.MAX_VALUE - 1);
            iterator.next = node;
            iterator = node;
            count--;
        }
        return head;
    }

    public void findKthTiTail1(Node node, Integer k) {
        int count = 0;
        Node iterator = node;
        while (iterator != null) {
            count++;
            iterator = iterator.next;
        }
        if (k > count) {
            throw new RuntimeException("不存在倒数第" + k + "个节点");
        }

        iterator = node;
        for (int i = 1; i < count - k + 1; i++) {
            iterator = iterator.next;
        }
        System.out.println(iterator.value);
    }

    public void findKthTiTail2(Node node, Integer k) {
        Node iterator = node;
        Node iterator1 = null;
        int i = 1;
        while (iterator != null) {
            if (iterator1 != null) {
                iterator1 = iterator1.next;
            }
            if (i == k) {
                iterator1 = node;
            }
            iterator = iterator.next;
            i++;
        }
        if (iterator1 == null) {
            System.out.println("11111111111111111111111111");
            throw new RuntimeException("不存在倒数第" + k + "个节点");
        }
        System.out.println(iterator1.value);
    }
    static class Node {
        public Integer value;
        public Node next;
    }
}

