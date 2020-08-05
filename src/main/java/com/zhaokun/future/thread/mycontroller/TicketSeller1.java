package com.zhaokun.future.thread.mycontroller;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 多线程下，并发的队列有ConcurrentLinkedDeque
 * @author zhaok
 */
public class TicketSeller1 {

    static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票号： " + i);
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    /**
                     * 多线程下的开发技巧，先操作poll()然后再判断，这样就不用再操作这个值，就不会出现问题。
                     */
                    String t = tickets.poll();
                    if (t == null) {
                        break;
                    } else {
                        System.out.println("销售了 --- " + t);
                    }
                }
            }).start();
        }
    }
}
