package com.zhaokun.future.thread.mycontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * countDownLatch 门栓 初始化值，调用countDown()会减一，当减到0的时候，await() 就可以执行了。
 *
 * @author zhaok
 */
public class MyController2 {

    List<Object> list = new ArrayList<>();
    static CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyController2 myController1 = new MyController2();

        new Thread(() -> {
            try {
                COUNT_DOWN_LATCH.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2结束了");
        }, "t2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myController1.add(new Object());
                System.out.println("add " + i);
                if (myController1.size() == 5) {
                    COUNT_DOWN_LATCH.countDown();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "t1").start();


    }

}
