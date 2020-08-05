package com.zhaokun.future.thread.concurrentMap;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zhaok
 */
public class CopyOnWriteList {

    public static void main(String[] args) {
        List<String> lists = new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] trs = new Thread[100];
        for (int i = 0; i < trs.length; i++) {
            Runnable runnable = () -> {
                for (int j = 0; j < 1000; j++) {
                    lists.add("a" + r.nextInt(10000));
                }
            };
            trs[i] = new Thread(runnable);
        }

        runAndComputeTime(trs);
        System.out.println(lists.size());

    }

    private static void runAndComputeTime(Thread[] trs) {

        long start = System.currentTimeMillis();

        Arrays.asList(trs).forEach(Thread::start);
        Arrays.asList(trs).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s1 = System.currentTimeMillis();
        System.out.println(s1 - start);
    }


}
