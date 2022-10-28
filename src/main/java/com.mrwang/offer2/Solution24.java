package com.mrwang.offer2;

import com.mrwang.offer2.util.LinkNode;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

/**
 *  翻转链表
 */
public class Solution24 {
    public static void main(String[] args) throws NoSuchMethodException, ExecutionException, InterruptedException {
        LinkNode linkNode = LinkNode.generateLinkedList(3);
        Solution24 solution24 = new Solution24();
        Method reverseList = Solution24.class.getMethod("reverseList", LinkNode.class);
        LinkNode result = TakeTimeCalculate.getResult(solution24, reverseList, linkNode);

    }

    public LinkNode reverseList(LinkNode node) {
        if (node == null) {
            return null;
        }
        LinkNode iterator = node;
        LinkNode next = node.getNext();
        LinkNode head = next == null ? iterator : next;
        iterator.setNext(null);
        while (next != null) {
            LinkNode next1 = next.getNext();
            if (next1 == null) {
                head = next;
            }
            next.setNext(iterator);
            iterator = next;
            next = next1;
        }
        return head;
    }
}
