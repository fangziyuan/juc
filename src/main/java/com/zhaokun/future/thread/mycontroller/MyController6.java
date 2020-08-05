package com.zhaokun.future.thread.mycontroller;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 写一个同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 使用wait和notifyAll 实现
 *
 * @author zhaok
 */
public class MyController6<T> {


    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while (lists.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        this.notifyAll();
    }

    public synchronized T get() {
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t1 = lists.removeFirst();
        count--;
        this.notifyAll();
        return t1;
    }

    public static void main(String[] args) {
        MyController6<String> c = new MyController6<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println("c  " + c.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName()+ " - " + j);
                    System.out.println("p  " + Thread.currentThread().getName() + " - " + j);
                }
            }, "p" + i).start();
        }
    }
}
