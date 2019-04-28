package com.github.maxwell.base.thread.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CompletionService 批量执行异步任务 实现询价系统
 */
@Slf4j
public class CompletableServiceDemo {
    //同步
    public Double getPrice1(String product) {
        return calculatePrice(product);
    }

    public Double getPrice2(String product) {
        return calculatePrice(product);
    }

    public Double getPrice3(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return Math.random();
    }

    public void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CompletableServiceDemo demo = new CompletableServiceDemo();
        //创建线程池
        ExecutorService excutorService = Executors.newFixedThreadPool(3);
        //创建CompletalbeService
        CompletionService<Double> cs = new ExecutorCompletionService<>(excutorService);
        //异步向电商1询价
        cs.submit(() -> demo.getPrice1("huanwei"));
        //异步向电商2询价
        cs.submit(() -> demo.getPrice2("iphone"));
        cs.submit(() -> demo.getPrice3("oppo"));
        //将询价结果异步保存到数据库
        //并计算最低报价
        AtomicReference<Double> m = new AtomicReference<>(8999.00);
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            excutorService.execute(() -> {
                Double r = null;
                try {
                    r = cs.take().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                log.info("r {}", r);
                m.set(Double.min(m.get(), r));
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("m {}", m);
    }
}
