package com.zhaokun.future.thread.threadtest;

import java.util.concurrent.*;

public class test_01 {

    private static final ExecutorService  service = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.MICROSECONDS.sleep(500);
            System.out.println("这里可以处理业务代码");
            return 1000;
        });

        service.submit(task);
        service.shutdown();
        System.out.println(task.get());

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> f = executorService.submit(() -> {
            TimeUnit.MICROSECONDS.sleep(500);
            return 1;
        });

        System.out.println(f.get());
        System.out.println(f.isDone());
        executorService.shutdown();

    }

}
