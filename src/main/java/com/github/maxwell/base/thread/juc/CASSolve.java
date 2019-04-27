package com.github.maxwell.base.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 使用AtomicStampedReference解决ABA问题
 */
@Slf4j
public class CASSolve {
    // 主内存共享变量，初始值为1，版本号为1
    private static AtomicStampedReference<Integer> atomicStampedReference = new
            AtomicStampedReference<>(1, 1);

    public static void main(String[] args) {
        // t1,期望将1改为10
        new Thread(() -> {
            // 第一次拿到的时间戳
            int stamp = atomicStampedReference.getStamp();
            log.info("{} 第1次时间 {} 值 {}", Thread.currentThread().getName(), stamp, atomicStampedReference.getReference());
            // 休眠5s,确保t2执行完ABA操作
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // t2将时间戳改为了3,cas失败
            boolean b = atomicStampedReference.compareAndSet(1, 10, stamp, stamp + 1);
            log.info("{} CAS是否成功 {}", Thread.currentThread().getName(), b);
            log.info("{} 当前最新时间 {} 最新值 {}", Thread.currentThread().getName(), atomicStampedReference.getStamp(), atomicStampedReference.getReference());
        }, "t1").start();

        // t2进行ABA操作
        new Thread(() -> {
            // 第一次拿到的时间戳
            int stamp = atomicStampedReference.getStamp();
            log.info("{} 第1次时间 {} 值 {}", Thread.currentThread().getName(), stamp, atomicStampedReference.getReference());
            // 休眠，修改前确保t1也拿到同样的副本，初始值为1
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 将副本改为20，再写入，紧接着又改为1，写入，每次提升一个时间戳，中间t1没介入
            atomicStampedReference.compareAndSet(1, 20, stamp, stamp + 1);
            log.info("{} 第2次时间 {} 值 {}", Thread.currentThread().getName(), stamp, atomicStampedReference.getReference());
            atomicStampedReference.compareAndSet(20, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            log.info("{} 第3次时间 {} 值 {}", Thread.currentThread().getName(), atomicStampedReference.getStamp() , atomicStampedReference.getReference());
        }, "t2").start();
    }

}
