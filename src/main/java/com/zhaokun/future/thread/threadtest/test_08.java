package com.zhaokun.future.thread.threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class test_08 {

    private static final ReentrantLock r1 = new ReentrantLock();
    private static final ReentrantLock r2 = new ReentrantLock();
    private static final ExecutorService service = newFixedThreadPool(2);

    void m1() {
        r1.lock();
            try {
                System.out.println("h1");
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } finally {
                r1.unlock();
            }
        }
    void m2() {
        r2.lock();
        try {
            System.out.println("h2");
        } finally {
            r2.unlock();
        }
    }

    public static void main(String[] args) {
        try {
            test_08 t = new test_08();
            service.submit(t::m1);
            service.submit(t::m2);
        } finally {
            service.shutdown();
        }


    }

}
