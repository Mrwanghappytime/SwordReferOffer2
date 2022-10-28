package com.mrwang.offer2.util;

import java.util.Random;

public class LinkNode<T> {
    private T value;
    private LinkNode next;

    public LinkNode(T value, LinkNode next) {
        this.value = value;
        this.next = next;
    }

    public LinkNode() {
    }

    public static LinkNode generateAscLinkedList(int count) {
        if (count == 0) {
            return null;
        }
        Random random = new Random();
        LinkNode head = new LinkNode();
        int value = random.nextInt(100) + 1;
        head.value = value;
        LinkNode iterator = head;

        while (count > 1) {
            LinkNode node = new LinkNode();
            node.value = value + random.nextInt(100) + 1;
            value = (int) node.value;
            iterator.next = node;
            iterator = node;
            count--;
        }
        return head;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public static LinkNode generateLinkedList(int count) {
        Random random = new Random();
        LinkNode head = new LinkNode();
        head.value = random.nextInt(Integer.MAX_VALUE - 1);
        LinkNode iterator = head;
        while (count > 1) {
            LinkNode node = new LinkNode();
            node.value = random.nextInt(Integer.MAX_VALUE - 1);
            iterator.next = node;
            iterator = node;
            count--;
        }
        return head;
    }
    public static LinkNode generateLinkedListCircle(int count) {
        Random random = new Random();
        LinkNode head = new LinkNode();
        head.value = random.nextInt(Integer.MAX_VALUE - 1);
        int i = random.nextInt(count - 2) + 1;
        System.out.println("i:," + i);
        LinkNode iterator = head;
        LinkNode start = null;
        while (count > 1) {

            LinkNode node = new LinkNode();
            node.value = random.nextInt(Integer.MAX_VALUE - 1);
            iterator.next = node;
            iterator = node;
            if (count == i) {
                start = iterator;
            }
            count--;
        }
        if (i == 1) {
            start = head;
        }
        System.out.println("start:" + start.value);
        iterator.next = start;
        return head;
    }

    public static void printNode(LinkNode node) {
        LinkNode iterator = node;
        System.out.println("begin print node:");
        while (iterator != null) {
            System.out.print(iterator.getValue() + " ");
            iterator = iterator.next;
        }
        System.out.println();
        System.out.println("end print node");
    }
}
