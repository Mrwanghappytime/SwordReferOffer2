package com.mrwang.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public abstract class MyAQS {

    private volatile AtomicReference<Node> head = new AtomicReference<>(null);

    private volatile AtomicReference<Node> tail = new AtomicReference<>(null);

    // state,当前锁状态
    private volatile AtomicInteger state = new AtomicInteger(0);

    private Thread holdThread;

    public Thread getHoldThread() {
        return holdThread;
    }

    public void setHoldThread(Thread holdThread) {
        this.holdThread = holdThread;
    }

    public int getState() {
        return state.get();
    }

    public boolean compareAndSetState(int expect, int update) {
        return state.compareAndSet(expect, update);
    }

    public boolean tryAcquire(int acquire) {
        throw new UnsupportedOperationException();
    }

    public boolean tryRelease(int acquire) {
        throw new UnsupportedOperationException();
    }

    public void acquire(int acquire) {
        if (!tryAcquire(acquire)) {
            addWaiter(instanceNewNode(), acquire);
        }
    }

    public void release(int acquire) {
        if (holdThread == Thread.currentThread()) {
            tryRelease(acquire);
            if (state.get() == 0 && head.get() != null && head.get().next != null) {
                LockSupport.unpark(head.get().next.thread);
            }
        }
    }

    protected Node instanceNewNode() {
        Thread thread = Thread.currentThread();
        Node node = tail.get();
        boolean success = false;
        Node node1 = new Node();
        node1.thread = thread;
        if (node != null) {

            node1.pre = node;
            success = tail.compareAndSet(node, node1);
        }
        if (success) {
            node.next = node1;
            return node1;
        }
        enq(node1);
        return node1;
    }

    protected void enq(Node node) {
        while(true) {
            Node node1 = tail.get();
            if (node1 == null) {
                Node node2 = new Node();
                if (tail.compareAndSet(node1, node2)) {
                    if (head.get() == null) {
                        head.compareAndSet(null, node2);
                    }
                }
            } else {
                node.pre = node1;
                if (tail.compareAndSet(node1, node)) {
                    node1.next = node;
                    return;
                }
            }
        }
    }

    protected void addWaiter(Node node, int acquire) {
        for (;;) {
            Node pre = node.pre;
            if (pre == head.get() && tryAcquire(acquire)) {
                pre.next = node.next;
                if (node.next != null) {
                    node.next.pre = pre;
                }
                node = null;
                break;
            } else {
                LockSupport.park();
            }
        }
    }



    private class Node {
        private Node pre;
        private Node next;
        private Thread thread;
        // condition,mode todo

    }
}
