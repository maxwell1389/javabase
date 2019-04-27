package com.github.maxwell.base.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch使用例子
 */
@Slf4j
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            log.info("子线程{} 正在执行", Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                log.info("子线程 {} 执行完毕", Thread.currentThread().getName());
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        new Thread(() -> {
            log.info("子线程 {} 正在执行", Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                log.info("子线程 {} 执行完毕", Thread.currentThread().getName());
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        log.info("等待两个线程执行完毕。。。");
        try {
            countDownLatch.await();
            log.info("两个线程已经执行完毕");
            log.info("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
