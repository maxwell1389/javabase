package com.github.maxwell.base.thread.threadlocals;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程本地存储ThreadLocal使用DEMO
 */
@Slf4j
public class ThreadLocalDemo {

    public static void main(String[] args) {
        new Thread(() -> {
            log.info("{} dateformat {} long {} long1 {}", Thread.currentThread().getName(), SafeDateFormat.get().format(new Date()), ThreadId.get(), ThreadId.get());
        }, "t1").start();

        new Thread(() -> {
            log.info("{} dateformat {} long {} long1 {}", Thread.currentThread().getName(), SafeDateFormat.get().format(new Date()), ThreadId.get(), ThreadId.get());
        },"t2").start();

        new Thread(() -> {
            log.info("{} dateformat {} long {} long1 {}", Thread.currentThread().getName(), SafeDateFormat.get().format(new Date()), ThreadId.get(), ThreadId.get());
        },"t3").start();
    }

    static class SafeDateFormat {
        //定义ThreadLocal变量
        static final ThreadLocal<DateFormat> threadLocal =
                ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        static DateFormat get() {
            return threadLocal.get();
        }
    }

    static class ThreadId {
        static final AtomicLong nextId = new AtomicLong(0);
        //定义ThreadLocal变量
        static ThreadLocal<Long> threadLocal = ThreadLocal.withInitial(() -> nextId.getAndIncrement());

        //此方法会为每个线程分配一个唯一Id
        static long get() {

            return threadLocal.get();
        }
    }

}
