package com.zhaokun.future.thread.mycontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyController1 {

    volatile List<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyController1 myController1 = new MyController1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myController1.add(new Object());
                System.out.println("add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(() -> {
            while (true) {
                if (myController1.size() == 5) {
                    break;
                }
            }
        }, "t2").start();
    }



}
