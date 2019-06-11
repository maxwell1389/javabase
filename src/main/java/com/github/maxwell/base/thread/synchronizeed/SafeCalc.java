package com.github.maxwell.base.thread.synchronizeed;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于线程安全计算类，使用Synchronized
 */
@Slf4j
public class SafeCalc {
    private volatile long quantiy = 0L;

    public long getQ() {
        return this.quantiy;
    }

    public synchronized void addOne() {
        this.quantiy += 1;
    }

    public static void main(String[] args) {
        SafeCalc safeCalc = new SafeCalc();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                safeCalc.addOne();
                log.info("Threadname {} quantity {}", Thread.currentThread().getName(), safeCalc.getQ());
            }).start();
        }
    }
}
