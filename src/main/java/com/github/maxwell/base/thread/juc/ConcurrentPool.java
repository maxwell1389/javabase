package com.github.maxwell.base.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * 练习多线程并发 Semaphore
 * 对象池的限流器
 *
 * @param <T>
 * @param <R>
 */
@Slf4j
public class ConcurrentPool<T, R> {
    //对象池子
    volatile List<T> pool = new Vector<T>();
    Semaphore semaphore = null;

    public ConcurrentPool(int poolSize, T t) {
        //初始化对象池
        for (int i = 0; i < poolSize; i++) {
            pool.add(t);
        }
        //初始化限流器
        this.semaphore = new Semaphore(poolSize);
    }

    R exec(Function<T, R> function) {
        T remove = null;
        try {
            log.info("线程 {} 申请资源前池子大小 {}", Thread.currentThread().getName(), pool.size());
            semaphore.acquire();
            //从池子移除
            remove = pool.remove(0);
            log.info("线程 {} 申请资源后池子大小 {}", Thread.currentThread().getName(), pool.size());
            return function.apply(remove);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            log.info("线程 {} 回收资源前池子大小 {}", Thread.currentThread().getName(), pool.size());
            semaphore.release();
            pool.add(remove);
            log.info("线程 {} 回收资源后池子大小 {}", Thread.currentThread().getName(), pool.size());
        }

        return null;
    }

    public static void main(String[] args) {
        ConcurrentPool<Integer, String> pool = new ConcurrentPool<>(10, 2);
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    pool.exec(t -> {
                        log.info("{} functoin函数执行 {}", Thread.currentThread().getName(), t);
                        return t.toString();
                    });
                }
            }).start();
        }
    }
}
