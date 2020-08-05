package com.zhaokun.future.thread.mycontroller;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 使用wait和notifyAll 实现
 *
 * @author zhaok
 */
public class MyController7<T> {


    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();
    public void put(T t) {
        try {
            lock.lock();
            while (lists.size() == MAX) {
                producer.await();
            }
            lists.add(t);
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public T get() {
        T t1 = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();
            }
            t1 = lists.removeFirst();
            producer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t1;
    }

    public static void main(String[] args) {
        MyController7<String> c = new MyController7<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
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
                    c.put(Thread.currentThread().getName()+ "  " + j);
                }
            }, "p" + i).start();
        }
    }
}
