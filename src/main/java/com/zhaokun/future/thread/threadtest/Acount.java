package com.zhaokun.future.thread.threadtest;

import java.util.concurrent.TimeUnit;

/**
 * 同步方法和非同步方法可以同时调用吗？
 * as: 可以
 */
public class Acount {

    String name;
    double balance;

    public synchronized void set(String name, double balance) throws InterruptedException {
        this.name = name;
        TimeUnit.SECONDS.sleep(1);
        this.balance = balance;
    }

    public double getBalance(String balance) {
        return this.balance;
    }

    public static void main(String[] args) throws InterruptedException {
        Acount acount = new Acount();
        new Thread(() -> {
            try {
                acount.set("zhangsan",100.0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(acount.getBalance("zhangsan"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(acount.getBalance("zhangsan"));
    }

}
