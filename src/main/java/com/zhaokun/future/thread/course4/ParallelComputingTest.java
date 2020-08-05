package com.zhaokun.future.thread.course4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author zhaok
 */
public class ParallelComputingTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> result = getPrime(1,200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");

        final int cupCoreNum = 8;
        ExecutorService service = newFixedThreadPool(cupCoreNum);

        MyTask t1 = new MyTask(1,80000);
        MyTask t2 = new MyTask(800001,130000);
        MyTask t3 = new MyTask(1300001,170000);
        MyTask t4 = new MyTask(170001,200000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
        service.shutdown();

    }

    static class MyTask implements Callable<List<Integer>> {

        int startPos, endPos;

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public List<Integer> call() {
            List<Integer> r = getPrime(startPos,endPos);
            return r;
        }
    }

    static boolean isPrime(int num) {
        for (int i = 2; i <= num/2; i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }

    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                results.add(i);
            }
        }
        return results;
    }

}
