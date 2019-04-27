package com.github.maxwell.base.thread.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskTest {
    public static void main(String[] args) {
        //创建任务T2的FutureTask
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        //创建任务T1的FutureTask
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));

        Thread t1 = new Thread(ft1);
        t1.start();
        Thread t2 = new Thread(ft2);
        t2.start();

        //等待线程T1执行结果
        try {
            log.info("ft1: {}",ft1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
