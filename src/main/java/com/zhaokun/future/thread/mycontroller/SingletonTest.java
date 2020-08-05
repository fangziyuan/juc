package com.zhaokun.future.thread.mycontroller;

/**
 * 单例
 * @author zhaok
 */
public class SingletonTest {

    private volatile static SingletonTest singletonTest = null;

    private SingletonTest() {}

    public static SingletonTest getSingletonTest() {
        if (singletonTest == null) {
            synchronized (SingletonTest.class) {
                if (singletonTest == null) {
                    singletonTest = new SingletonTest();
                }
            }
        }
        return singletonTest;
    }


    public static void main(String[] args) {
        SingletonTest singletonTest = getSingletonTest();
        SingletonTest singletonTest1 = getSingletonTest();
        System.out.println(singletonTest == singletonTest1);
    }

}
