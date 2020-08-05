package com.zhaokun.future.thread.threadtest;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newScheduledThreadPool;

public class test_04 {

    public static void main(String[] args) {
        ScheduledExecutorService service = newScheduledThreadPool(4);
        System.out.println(Runtime.getRuntime().availableProcessors());
        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },0,500, TimeUnit.MICROSECONDS);
    }

}
