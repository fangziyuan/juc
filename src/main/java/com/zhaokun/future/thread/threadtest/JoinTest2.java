package com.zhaokun.future.thread.threadtest;

/**
 * @author zhaok
 */
public class JoinTest2 {

    public static void main(String[] args) {
        final Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " run 1"), "T1");

        final Thread t2 = new Thread(() -> {

            try {
                t1.join(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " run 2");
        }, "T2");

        final Thread t3 = new Thread(() -> {

            try {
                t2.join(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " run 3");
        }, "T3");


        t1.start();
        t2.start();
        t3.start();
    }

}
