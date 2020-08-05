package com.zhaokun.future.future;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class InNumberController {

    @Autowired
    private AsyncTaskFuture taskService;

    @GetMapping("/test")
    public long test() throws InterruptedException, ExecutionException {
        long s = System.currentTimeMillis();
        List<JSONObject> allList = new ArrayList<>();
        Future<List<JSONObject>> task1 = taskService.task1();
		Future<List<JSONObject>> task2 = taskService.task2();
        Future<List<JSONObject>> task3 = taskService.task3();
        allList.addAll(task1.get());
        allList.addAll(task2.get());
        allList.addAll(task3.get());
        System.out.println(allList.size());

        long e = System.currentTimeMillis();
        System.out.println("task总耗时:" + (e - s));

        return  (e - s);

    }


}
