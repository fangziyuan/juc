package com.zhaokun.future.future;

import com.alibaba.fastjson.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Component
public class AsyncTaskFuture {


    @Async
    public Future<List<JSONObject>> task1() throws InterruptedException {
        List<JSONObject> drugList = new ArrayList<>();

        for (int i = 0; i < 50000; i++) {
            JSONObject json = new JSONObject();
            json.put("a" + i,i);
            drugList.add(json);
        }
        return new AsyncResult<>(drugList);
    }

    @Async
    public Future<List<JSONObject>> task2() throws InterruptedException {
        List<JSONObject> leftList = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            JSONObject json = new JSONObject();
            json.put("b" + i,i);
            leftList.add(json);
        }
        return new AsyncResult<>(leftList);
    }

    @Async
    public Future<List<JSONObject>> task3() throws InterruptedException {
        List<JSONObject> leftList = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            JSONObject json = new JSONObject();
            json.put("c" + i,i);
            leftList.add(json);
        }
        return new AsyncResult<>(leftList);
    }

    @Async
    public Future<String> task4() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task4任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<String>("task4执行完毕");
    }

}
