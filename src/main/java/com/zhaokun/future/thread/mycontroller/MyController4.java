package com.zhaokun.future.thread.mycontroller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author zhaok
 */
public class MyController4 {

    private static final ExecutorService executorService = newFixedThreadPool(2);
    Lock lock = new ReentrantLock(); // AQS

    void m1() {
        lock.lock();
        try {
           for (int i = 0; i < 10; i++) {
               TimeUnit.SECONDS.sleep(1);
               System.out.println(i);
           }
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
       }
    }

    void m2() {
        boolean b = false;

        try {
            b = lock.tryLock(5,TimeUnit.SECONDS);
            System.out.println("m2" + b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (b) {
                System.out.println("m2() 获取锁了");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        MyController4 m = new MyController4();
//        new Thread(m::m2).start();
        try {
            executorService.execute(m::m1);
            TimeUnit.SECONDS.sleep(1);
            executorService.submit(m::m2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
//        new Thread(m::m1).start();

    }



}
