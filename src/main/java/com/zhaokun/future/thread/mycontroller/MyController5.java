package com.zhaokun.future.thread.mycontroller;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 比synchronized灵活，能够实现它所有的功能，
 * 还要更加灵活，有lock.tryLock()方法 还可以指定超时时间，
 * 还有lock.lockInterruptibly(),在一个线程等待锁的过程中，可以被打断
 * ReenTrantLock还可以指定为公平
 *
 * @author zhaok
 */
public class MyController5 extends Thread{
    
    private static final ReentrantLock lock = new ReentrantLock(false);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        MyController5 myController5 = new MyController5();
        Thread thread1 = new Thread(myController5);
        Thread thread2 = new Thread(myController5);

        thread1.start();
        thread2.start();

    }
    
    
    
    
    
}
