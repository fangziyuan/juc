package com.zhaokun.future.thread.queuetest;

import java.util.Stack;

/**
 * @author zhaok
 */
public class StackTest {

    public static void main(String[] args) {
        Stack v = new Stack();
        //依次将三个元素push入"栈"
        v.push("疯狂Java讲义");
        v.push("轻量级Java EE企业应用实战");
        v.push("疯狂Android讲义");

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(v);

        //访问第一个元素，但并不将其pop出"栈"，输出：疯狂Android讲义
        //pop出第一个元素，输出：疯狂Android讲义
        System.out.println(v.peek());
        //依然输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(v);
        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战]
        System.out.println(v.pop());
        System.out.println(v);
    }

}
