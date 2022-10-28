package com.mrwang.offer2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TakeTimeCalculate {

    public static void submit(Object o, Method method, Object... args) {
        CountDownLatch count = new CountDownLatch(1);
        new Thread(() -> {
            calculate(method.getName(), count);

            try {
                count.await();
                method.invoke(o, args);
            } catch (IllegalAccessException e) {

            } catch (InvocationTargetException e) {

            } catch (InterruptedException e) {

            }
        }).start();
    }
    public static void calculate(String prefix, CountDownLatch count) {
        Thread thread = Thread.currentThread();
        new Thread(() -> {

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(prefix + ",begin");
            count.countDown();
            long currentTimeMillis = System.currentTimeMillis();
            try {
                thread.join();
            } catch (InterruptedException e) {

            }
            long currentTimeMillis1 = System.currentTimeMillis();
            System.out.println(prefix + ",end");
            System.out.println(prefix + ",use time: " + (currentTimeMillis1 - currentTimeMillis) + "ms");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }).start();
    }

    public static  <T> T getResult(Object o, Method method, Object... args) throws ExecutionException, InterruptedException {
        CountDownLatch count = new CountDownLatch(1);
        FutureTask futureTask  = new FutureTask(() -> {
            calculate(method.getName(), count);
            try {
                count.await();
                return method.invoke(o, args);
            } catch (IllegalAccessException e) {

            } catch (InvocationTargetException e) {

            } catch (InterruptedException e) {

            }
            return null;
        });
        new Thread(futureTask).start();
        return (T) futureTask.get();
    }
}
