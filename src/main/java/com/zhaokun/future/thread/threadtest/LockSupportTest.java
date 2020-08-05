package com.zhaokun.future.thread.threadtest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println("今天周末，我要吃鸡");
            LockSupport.park();// 阻塞，正在吃鸡，（等女朋友电话，去逛街）
            System.out.println("陪女朋友逛街！");
        });

        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("打电话");
            LockSupport.unpark(thread);
        });

        thread.start();
        thread1.start();

//        LockSupport.park();
//        System.out.println("end");

    }

    public static void count() {
        System.out.println("后输出");

    }

    public static void second() {

        System.out.println("先输出");
    }


}
