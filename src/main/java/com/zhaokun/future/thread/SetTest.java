package com.zhaokun.future.thread;

import java.util.*;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.*;

/**
 *
 * 测试set去重
 * @author zhaok
 */
public class SetTest {

    ExecutorService executorService = newCachedThreadPool();
    ExecutorService executorService1 = newFixedThreadPool(10);
    ExecutorService executorService2 = newSingleThreadExecutor();
    ExecutorService executorService3 = newScheduledThreadPool(10);
    ExecutorService executorService4 = newWorkStealingPool();

    public static void main(String[] args) {

        /*List<Integer> stringList = new ArrayList<>();
        int num = 1;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            stringList.add(i);
            stringList.add(num + i);
        }
        System.out.println(System.currentTimeMillis() - start + "ms");
        System.out.println(stringList);
        start = System.currentTimeMillis();
        Set<Integer> setList = new HashSet<>(stringList);
        System.out.println(System.currentTimeMillis() - start + "ms");
        System.out.println(setList);*/

        List<Integer> stringList = new ArrayList<>();
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            stringList.add(i);
        }
        long time1 = System.currentTimeMillis() - start1;
        System.out.println("stringList:" + time1 + "ms");

        long start2 = System.currentTimeMillis();
        Set<Integer> setList = new HashSet<>();
        for (int i = 0; i < 1000000; i++) {
            setList.add(i);
        }
        long time2 = System.currentTimeMillis() - start2;
        System.out.println("setList:" + time2 + "ms");

        long start3 = System.currentTimeMillis();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 1000000; i++) {
            linkedList.add(i);
        }

        long time3 = System.currentTimeMillis() - start3;
        System.out.println("linkedList:" + time3 + "ms");

    }

}
