package com.zhaokun.future.thread.queuetest;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaok
 */
public class LinkedBlockingQueueTest {

    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
    static Random random = new Random();

    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i);
                    TimeUnit.MICROSECONDS.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (;;) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " take - " + strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c" + i).start();
        }

    }

}
