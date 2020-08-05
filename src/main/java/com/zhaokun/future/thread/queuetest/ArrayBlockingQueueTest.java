package com.zhaokun.future.thread.queuetest;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhaok
 */
public class ArrayBlockingQueueTest {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.add("a" + i);
        }

//        strs.put("aaa");
//        strs.add("aaa");
        boolean aaa = strs.offer("aaa");
        System.out.println(aaa);
//        strs.offer("aaa", 1 , TimeUnit.SECONDS);

        System.out.println(strs);
    }

}
