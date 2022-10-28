package com.mrwang.offer2;

import com.mrwang.offer2.util.LinkNode;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 *  找到循环链表的入口
 */
public class Solution23 {
    public static void main(String[] args) throws NoSuchMethodException, ExecutionException, InterruptedException {
        LinkNode linkNode = LinkNode.generateLinkedListCircle(1000000);
        Solution23 solution23 = new Solution23();
        Method isCircleLinkedList = solution23.getClass().getMethod("isCircleLinkedList", LinkNode.class);
        TakeTimeCalculate.submit(solution23, isCircleLinkedList, linkNode);
        Method getCircleBeginNode = solution23.getClass().getMethod("getCircleBeginNode", LinkNode.class);
        LinkNode linkNode2 = TakeTimeCalculate.getResult(solution23, getCircleBeginNode, linkNode);
        System.out.println("begin:" + linkNode2.getValue());
    }

    public void isCircleLinkedList(LinkNode linkNode) {
        LinkNode node = linkNode;
        LinkNode node1 = node.getNext();
        int i = 1;
        while (node1 != null && node1 != node) {
            if ((i & 1) == 0) {
                node = node.getNext();
            }
            node1 = node1.getNext();
            i++;
        }
        System.out.println("linknode is circle link:" + (node1 == node));
    }

    public LinkNode getCircleBeginNode(LinkNode linkNode) {
        Set<LinkNode> set = new HashSet<>();
        LinkNode iterator = linkNode;
        while(iterator != null && !set.contains(iterator)) {
            set.add(iterator);
            iterator = iterator.getNext();
        }
        return iterator;
    }

    public LinkNode getCircleBeginNode1(LinkNode linkNode) {
        Set<LinkNode> set = new HashSet<>();
        LinkNode iterator = linkNode;
        while(iterator != null && !set.contains(iterator)) {
            set.add(iterator);
            iterator = iterator.getNext();
        }
        return iterator;
    }

}
