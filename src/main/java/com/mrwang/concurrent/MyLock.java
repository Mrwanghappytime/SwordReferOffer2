package com.mrwang.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyLock extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setState(0);
        return true;
    }

    public void lock() {
        acquire(1);
    }

    public void unlock() {
        release(1);
    }




    public static void main(String[] args) throws InterruptedException {
        MyLock myLock = new MyLock();
        new Thread(() -> {
            myLock.lock();
            System.out.println("线程1获得锁");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {

            }finally {
                myLock.unlock();
                System.out.println("线程1释放锁");
            }
        }).start();

        System.out.println("线程2准备获得锁");
        Thread.sleep(1000);
        myLock.lock();
        System.out.println("线程2获得锁");
        try {

        } finally {
            myLock.unlock();
            System.out.println("线程2释放锁");
        }
    }
}
