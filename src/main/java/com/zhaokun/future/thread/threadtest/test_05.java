package com.zhaokun.future.thread.threadtest;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 *
 * http://www.cnblogs.com/nexiyi/p/java_memony_model_and_thread.html
 *
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 */
public class test_05 {

    volatile int count = 0;
    // 主要是控制某一个方法加锁，就尽量减小锁的范围
    synchronized void m() {
        for (int i = 0; i < 100000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        test_05 test = new test_05();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(test::m,"thread-"+i));
        }
        long startTime = System.currentTimeMillis();
        threads.forEach(Thread::start);
        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(test.count);
        System.out.println(System.currentTimeMillis() - startTime);
    }



}
