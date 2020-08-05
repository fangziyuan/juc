package com.zhaokun.future.thread.threadtest;

/**
 * @author zhaok
 */
public class JoinTest3 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                System.out.println(i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                System.out.println(i);
            }
        });


        Thread t3 = new Thread(() -> {
            for (int i = 20; i < 30; i++) {
                System.out.println(i);
            }
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
    }

}
