package com.zhaokun.future.thread.concurrentMap;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author zhaok
 */
public class ConcurrentMapTest {
    static ExecutorService executorService = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        Map<String,String> map = new Hashtable<>(); 74 ms
        //Map<String,String> map = new HashMap<>(); // 88 ms
        Map<String,String> map = new ConcurrentHashMap<>(16); //90ms
//         Map<String,String> map = new ConcurrentSkipListMap<>(); // 75 ms
        Random random = new Random();

        Thread[] trs = new Thread[100];
        CountDownLatch countDownLatch = new CountDownLatch(trs.length);
        long start = System.currentTimeMillis();

        Future future = executorService.submit(() -> {
            for (int j = 0; j < 10000; j++) {
                map.put("a" + random.nextInt(10000), "a" + random.nextInt(10000));
                countDownLatch.countDown();
            }
            return 1;
        });
//            trs[i] = new Thread(()-> {
//                for (int j = 0; j < 10000; j++) {
//                    map.put("a" + random.nextInt(10000), "a" + random.nextInt(10000));
//                    countDownLatch.countDown();
//                }
//            });

//        Arrays.asList(trs).forEach(Thread::start);

        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
//        System.out.println(future.get());
        System.out.println(end - start + " ms");


        executorService.shutdown();
    }

}
