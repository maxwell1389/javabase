package com.github.maxwell.base.thread.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * T1Task需要执行的任务
 * 洗水壶、烧开水、泡茶
 */
@Slf4j
public class T1Task implements Callable<String> {
    FutureTask<String> ft2;
    //T1任务需要T2任务的FutureTask

    public T1Task(FutureTask<String> ft2) {
        this.ft2 = ft2;
    }

    @Override
    public String call() throws Exception {
        log.info("T1: 洗水壶。。。");
        TimeUnit.SECONDS.sleep(1);
        log.info("T1：烧开水。。。");
        TimeUnit.SECONDS.sleep(15);
        //获取T2线程的茶叶
        String tf = ft2.get();
        log.info("T1：拿到茶叶：{}", tf);

        log.info("T1：泡茶。。。");
        return "上茶：" + tf;
    }
}
