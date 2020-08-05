package com.zhaokun.future.thread.threadtest;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class JoinTest {

    static final ExecutorService executor = newSingleThreadExecutor();

    static JoinTest t=new JoinTest();

    /*class T1 extends Thread{

        @Override
        public void run() {

            //T3线程中要处理的东西
            System.out.println("T1线程执行");


        }

    }

    class T2 extends Thread{

        @Override
        public void run() {

            //T3线程中要处理的东西
            System.out.println("T2线程执行");
            t.new T1().start();


        }

    }

    class T3 extends Thread{

        @Override
        public void run() {

            //T3线程中要处理的东西
            System.out.println("T3线程执行");
            t.new T2().start();


        }


    }


    public static void main(String[] args) {

        t.new T3().start();

    }*/

        /*class T1 extends Thread{
            @Override
            public void run() {

                //T3线程中要处理的东西
                System.out.println("T1线程执行");
                for(int i=0;i<10;i++){
                    System.out.println(this.getName() + ":" + i);
                }

            }

        }

        class T2 extends Thread{

            @Override
            public void run() {

                //T3线程中要处理的东西
                System.out.println("T2线程执行");

                for(int i=0;i<10;i++){
                    System.out.println(this.getName() + ":" + i);
                }


            }

        }

        class T3 extends Thread{

            @Override
            public void run() {

                //T3线程中要处理的东西
                System.out.println("T3线程执行");

                for(int i=0;i<10;i++){
                    System.out.println(this.getName() + ":" + i);
                }


            }


        }


        public static void main(String[] args) {

            try {
                t.new T3().start();//启动t3线程
                t.new T3().join();//阻塞主线程，执行完t3再返回
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                t.new T1().start();//启动t1线程
                t.new T1().join();//阻塞主线程，执行完t1再返回
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                t.new T2().start();//启动t2线程
                t.new T2().join();//阻塞主线程，执行完t2再返回
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }*/

    public static void main(String[] args) {
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run 1");
            }
        }, "T1");
        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run 2");
                try {
                    t1.join(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2");
        final Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run 3");
                try {
                    t2.join(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T3");
        // method1
        //t1.start();
        //t2.start();
        //t3.start();

        //        method 2 使用 单个任务的线程池来实现。保证线程的依次执行

        executor.submit(t1);
        executor.submit(t2);
        executor.submit(t3);
        executor.shutdown();
    }

}
