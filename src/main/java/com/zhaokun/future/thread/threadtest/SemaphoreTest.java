package com.zhaokun.future.thread.threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;
    private static ExecutorService service = newFixedThreadPool(THREAD_COUNT);
    private static Semaphore semaphore = new Semaphore(10);

    static int num = 1;
    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            service.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(num++);
                    TimeUnit.SECONDS.sleep(10);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }




}
