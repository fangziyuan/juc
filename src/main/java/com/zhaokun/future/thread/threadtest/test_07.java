package com.zhaokun.future.thread.threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class test_07 {

    private static String h1 = "h1";
    private static String h2 = "h2";

    private static final ExecutorService service = newFixedThreadPool(2);

    void m1() {
        synchronized (h1) {
            try {
                System.out.println("h1");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void m2() {
        synchronized (h2) {
            System.out.println("h2");
        }
    }

    public static void main(String[] args) {
        try {
            test_07 t = new test_07();
            service.submit(t::m1);
            service.submit(t::m2);
        } finally {
            service.shutdown();
        }


    }

}
