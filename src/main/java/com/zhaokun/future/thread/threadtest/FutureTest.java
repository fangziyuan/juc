package com.zhaokun.future.thread.threadtest;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zhaok
 */
public class FutureTest {


    /**
     * new ThreadPoolExecutor(nThreads, nThreads,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>())
     */
    private static ExecutorService executor = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(100));

    public static void main(String[] args) {
        List<JSONObject> allResultJson = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        Future<List<JSONObject>> future = executor.submit(() -> {
            System.out.println("====task start===");
            List<JSONObject> list = new LinkedList<>();
            // 处理生活方式
            getListJson(list);
            allResultJson.addAll(list);
            // 处理疾病
            List<JSONObject> medicineList = new ArrayList<>();
            getMedicineListJson(medicineList);
            allResultJson.addAll(medicineList);
            // 处理药物
            List<JSONObject> drugList = new ArrayList<>();
            getDrugListJson(drugList);
            allResultJson.addAll(drugList);
            System.out.println("====task end===");
            return allResultJson;
        });
        try {
            List<JSONObject> futureList = future.get(300, TimeUnit.MILLISECONDS);
            int size = futureList.size();
            System.out.println(size);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime) + " ms");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

    }

    private static void getDrugListJson(List<JSONObject> drugList) {
        System.out.println("drugList 开始处理" + DateUtil.now());
        for (int i = 0; i < 50000; i++) {
            JSONObject json = new JSONObject();
            json.put("c" + i, i);
            drugList.add(json);
        }
    }

    private static void getMedicineListJson(List<JSONObject> medicineList) {
        System.out.println("medicineList 开始处理" + DateUtil.now());
        for (int i = 0; i < 50000; i++) {
            JSONObject json = new JSONObject();
            json.put("b" + i, i);
            medicineList.add(json);
        }
    }


    private static void getListJson(List<JSONObject> list) {
        System.out.println("list 开始处理" + DateUtil.now());
        for (int i = 0; i < 50000; i++) {
            JSONObject json = new JSONObject();
            json.put("a" + i, i);
            list.add(json);
        }
    }


}
