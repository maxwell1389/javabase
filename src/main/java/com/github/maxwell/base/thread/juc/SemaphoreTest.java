package com.github.maxwell.base.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * Semaphore使用例子
 */
@Slf4j
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        new Thread(() -> {
            try {
                log.info("{} 准备获取信号灯-A", Thread.currentThread().getName());
                semaphore.acquire();
                log.info("{} 已获取信号灯-A", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                log.info("{} 准备获取信号灯-B", Thread.currentThread().getName());
                semaphore.release();
                log.info("{} 已获取信号灯-B",Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() ->{
            try {
                log.info("{} 准备获取信号灯-C", Thread.currentThread().getName());
                semaphore.acquire();
                log.info("{} 已获取信号灯-C", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

/*        //检查线程
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("每2秒释放一次信号灯");
                semaphore.release();
                log.info("信号灯已释放");
            }
        }, 2000, 2000); //每2秒释放一次*/
    }
}
