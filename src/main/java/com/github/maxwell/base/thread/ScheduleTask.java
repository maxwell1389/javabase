package com.github.maxwell.base.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleTask {

    private static void abc(){
        System.out.println(Thread.currentThread().getName() + " run ");
        System.out.println(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
               abc();
            }
        }, 10, TimeUnit.SECONDS);
        executor.shutdown();
        System.out.println(System.currentTimeMillis());
    }
}
