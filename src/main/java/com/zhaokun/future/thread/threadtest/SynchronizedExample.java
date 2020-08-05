
package com.zhaokun.future.thread.threadtest;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author zhaok
 */
public class SynchronizedExample {
    int a = 0;
    boolean flag = false;
    public static void main(String[] args) {

        Object o = new Object();

        synchronized (o) {}

        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        /*SynchronizedExample synchronizedExample = new SynchronizedExample();
        synchronizedExample.reader();
        synchronizedExample.writer();
        synchronizedExample.reader();*/
    }

    public synchronized void writer() {
        a = 1;
        flag = true;
        System.out.println("writer() start");
        System.out.println("a :" + a + ", flag : " + flag);
        System.out.println("writer() end");
    }

    public synchronized void reader() {
        System.out.println("reader() start");
        if (flag) {
            int i = a;
            System.out.println("i :" + i + ", flag : " + flag);
        }
        System.out.println("reader() end");
    }

}
