package com.zhaokun.future.thread.queuetest;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueueTest {

    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            // add
            queue.offer( "a" + i);
        }
        System.out.println(queue);
        System.out.println(queue.size());
        // poll 是弹出队头一个，
        System.out.println(queue.poll());
        // peek 是查看对斗一个元素，但是不删除
        System.out.println(queue.peek());
        System.out.println(queue.size());
    }


}
