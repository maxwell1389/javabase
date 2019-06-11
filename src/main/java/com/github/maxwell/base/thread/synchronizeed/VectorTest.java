package com.github.maxwell.base.thread.synchronizeed;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * 竞态条件
 */
@Slf4j
public class VectorTest {
    public void addIfNotExits(Vector v, Object obj) {
        log.info("{} v {} obj {}", Thread.currentThread().getName(), v , obj);
        if (!v.contains(obj)) {
            v.add(obj);
            log.info("{} v {} add obj {}", Thread.currentThread().getName(), v , obj);
        }
        log.info("{} v {} after obj {}", Thread.currentThread().getName(), v , obj);
    }

    public void addIfNotExits1(List v, Object obj) {
        log.info("{} obj {}", Thread.currentThread().getName(), obj);
        if (!v.contains(obj)) {
            v.add(obj);
            log.info("{} add obj {}", Thread.currentThread().getName(), obj);
        }
        log.info("{} after obj {}", Thread.currentThread().getName(), obj);
    }

    public static void main(String[] args) {
        Vector vector = new Vector();

        List<String> lst = Lists.newArrayList();/*        vector.add("1");
        vector.add("2");
        vector.add("2");*/
//        lst.add("0");
//        lst.add("1");
//        lst.add("2");
        VectorTest vectorTest = new VectorTest();
        int counts = 2;
        CountDownLatch latch = new CountDownLatch(counts);
        for (int i = 0; i < counts; i++) {
            String v = String.valueOf(i);
            new Thread(()->{
                vectorTest.addIfNotExits(vector, "1");
            }).start();
            String v1 = String.valueOf(i);
            new Thread(()->{
                vectorTest.addIfNotExits(vector, "1");
//                vectorTest.addIfNotExits1(lst, v);
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("vector {}",vector);
//        log.info("lst {}",lst);

    }
}
