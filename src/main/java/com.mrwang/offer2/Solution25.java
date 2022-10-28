package com.mrwang.offer2;

import com.mrwang.offer2.util.LinkNode;

import java.util.Random;

/**
 * 合并两条递增的链表，结果也为递增链表
 */
public class Solution25 {
    public static void main(String[] args) {
        Random r = new Random();
        while(true) {
            LinkNode linkNode = LinkNode.generateAscLinkedList(r.nextInt(10));
            LinkNode.printNode(linkNode);
            LinkNode linkNode1 = LinkNode.generateAscLinkedList(r.nextInt(10));
            LinkNode.printNode(linkNode1);
            LinkNode linkNode2 = mergeSortLinkList(linkNode, linkNode1);
            LinkNode.printNode(linkNode2);

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }

    public static LinkNode mergeSortLinkList(LinkNode node1, LinkNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        }
        LinkNode iterator1 = node1;
        LinkNode iterator2 = node2;
        LinkNode iterator3 = null;
        LinkNode head = null;
        LinkNode next = null;
        while (iterator2 != null || iterator1 != null) {
            if (iterator1 == null) {
                next = iterator2;
                iterator2 = iterator2.getNext();
            } else if (iterator2 == null) {
                next = iterator1;
                iterator1 = iterator1.getNext();
            } else {
                boolean next1 = (int)iterator1.getValue() > ((int)iterator2.getValue());
                next = next1 ? iterator2 : iterator1;
                if (next1) {
                    iterator2 = iterator2.getNext();
                } else {
                    iterator1 = iterator1.getNext();
                }
            }
            if (head == null) {
                head = next;
                iterator3 = head;
            } else {
                iterator3.setNext(next);
                iterator3 = next;
            }
        }
        return head;
    }

}
