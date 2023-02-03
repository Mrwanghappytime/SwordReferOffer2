package com.mrwang.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockThreadState {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        ReentrantLock reentrantLock1 = new ReentrantLock(true);
        new Thread(() -> {
            System.out.println("线程1进入成功");

            reentrantLock.lock();
            try {

                Thread.sleep(1000);
                reentrantLock1.lock();
                System.out.println("线程1执行成功");

            } catch (InterruptedException e) {

            } finally {
              if (reentrantLock != null && reentrantLock.isHeldByCurrentThread()) {
                  reentrantLock.unlock();
              }
              if (reentrantLock1 != null && reentrantLock1.isHeldByCurrentThread()) {
                  reentrantLock1.unlock();
              }
            }
        }).start();
        Thread thread = new Thread(() -> {
            System.out.println("线程2进入成功");

            reentrantLock1.lock();
            try {

                Thread.sleep(1000);
                reentrantLock.lock();
                System.out.println("线程2执行成功");

            } catch (InterruptedException e) {

            } finally {
                if (reentrantLock != null && reentrantLock.isHeldByCurrentThread()) {
                    reentrantLock.unlock();
                }
                if (reentrantLock1 != null && reentrantLock1.isHeldByCurrentThread()) {
                    reentrantLock1.unlock();
                }
            }
        });
        thread.start();
        Thread.sleep(2000);

        System.out.println(thread.getState());
    }
}
