package com.github.maxwell.base.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier使用例子
 */
@Slf4j
public class CyclicBarrierTest {

    private static class Write extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Write(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            log.info("{} 正在写入数据...", Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                log.info("{} 写入数据完毕", Thread.currentThread().getName());
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException be) {
                be.printStackTrace();
            }
            log.info("{} 所有线程写入完毕，继续处理其他任务。。。", Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        int barrier = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(barrier);
        for (int i = 0; i < barrier; i++) {
            new Write(cyclicBarrier).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("CyclicBarrier 重用");
        for (int i = 0; i < barrier; i++) {
            new Write(cyclicBarrier).start();
        }
    }
}
