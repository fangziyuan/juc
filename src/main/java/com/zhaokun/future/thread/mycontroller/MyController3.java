package com.zhaokun.future.thread.mycontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * wait 和 notify
 * notify 不释放锁， wait释放锁
 *
 * notify 会在本线程执行完成后释放锁。要是想让释放锁的话，可以用wait()方法，等唤醒的别线程执行完成后再调用notify()方法。
 * @author zhaok
 */
public class MyController3 {

    volatile List<Object> list = new ArrayList<>();

    private static final Object o = new Object();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        MyController3 myController1 = new MyController3();

        new Thread(() -> {
            synchronized (o) {
                if (myController1.size() != 5) {
                    try {
                        o.wait();
                        System.out.println("t2 结束了");
                        o.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10; i++) {
                    myController1.add(new Object());
                    System.out.println("add " + i);

                    if (myController1.size() == 5) {
                        o.notifyAll();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    /*try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        },"t1").start();
    }



}
