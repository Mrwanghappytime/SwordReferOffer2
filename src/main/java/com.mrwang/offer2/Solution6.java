package com.mrwang.offer2;

import java.util.Stack;

/**
 *  从尾部到头部打印链表节点
 *  1. 使用栈存储节点node
 *  2. 使用递归打印----存在栈过深问题，建议使用栈
 */
public class Solution6 {
    public static void main(String[] args) {
        Node node = new Node();
        node.key = 1;
        node.next = null;
        printf(node);
        printf2(node);
    }

    private static void printf2(Node node) {
        Stack<Node> stack = new Stack<>();
        Node node1 = node;
        while(node1 != null) {
            stack.push(node1);
            node1 = node1.next;
        }

        while(!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.println(pop.key);
        }
    }

    private static void printf(Node node) {
        if (node.next != null) {
            printf(node.next);
        }
        System.out.println(node.key);
    }
}
class Node {
    public int key;
    public Node next;
}



