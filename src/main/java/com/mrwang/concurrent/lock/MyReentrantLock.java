package com.mrwang.concurrent.lock;

public class MyReentrantLock {

    private MySync sync;

    public MyReentrantLock() {
        this(false);
    }

    public MyReentrantLock(boolean fair) {
        sync = fair ? new FairSync() : new NoFairSync();
    }

    public void lock() {
        sync.lock();
    }

    public void unlock() {
        sync.unlock();
    }

    private class MySync extends MyAQS{
        public void lock() {
            acquire(1);
        }

        public void unlock() {
            release(1);
        }

    }


    private class FairSync extends MySync {
        @Override
        public boolean tryAcquire(int acquire) {
            if (getHoldThread() == null || (getHoldThread() != null && getHoldThread() == Thread.currentThread())) {
                int state = getState();
                int result = state + acquire;
                if (compareAndSetState(state, result)) {
                    setHoldThread(Thread.currentThread());
                    return true;
                }
                return false;
            } else {
                return false;
            }
        }

        @Override
        public boolean tryRelease(int acquire) {
            if (getHoldThread() == null || getHoldThread() != Thread.currentThread()) {
                throw new RuntimeException("当前没有持有锁，不能释放");
            }
            int state = getState();
            int result = state - acquire;
            if (result < 0) {
                throw new RuntimeException("当前没有持有锁，不能释放");
            }
            return compareAndSetState(state, result);
        }
    }

    private class NoFairSync extends MySync {
        @Override
        public boolean tryAcquire(int acquire) {
            return super.tryAcquire(acquire);
        }

        @Override
        public boolean tryRelease(int acquire) {
            return super.tryRelease(acquire);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyReentrantLock myReentrantLock = new MyReentrantLock(true);
        new Thread(() -> {
            myReentrantLock.lock();
            System.out.println("线程1获得锁");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {

            } finally {
                myReentrantLock.unlock();
                System.out.println("线程1释放锁");
            }
        }).start();

        Thread.sleep(1000);
        myReentrantLock.lock();
        System.out.println("线程2获得锁");
        try {

        }  finally {
            myReentrantLock.unlock();
            System.out.println("线程2释放锁");
        }
    }
}
