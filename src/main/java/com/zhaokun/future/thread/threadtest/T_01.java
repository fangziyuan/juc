package com.zhaokun.future.thread.threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class T_01 {

    private static final ExecutorService service = newFixedThreadPool(5);

    private static int count = 10;
    private static Object lock = new Object();
    private static Lock lock_01 = new ReentrantLock();

    public static void m() {
        synchronized (lock) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count-- " + count);
        }
    }

    public static void m1() {
        try {
            count--;
            System.out.println(Thread.currentThread().getName() + " count-- " + count);
        } finally {
            lock_01.unlock();
        }
        lock_01.lock();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            service.submit(T_01::m1);
        }
        service.shutdown();
        System.out.println(service);
    }



}
