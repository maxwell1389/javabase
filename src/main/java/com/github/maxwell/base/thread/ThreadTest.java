package com.github.maxwell.base.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/
public class ThreadTest {

    private static volatile Boolean flag = true;
    private static AtomicInteger num = new AtomicInteger();
    private static final Integer TOTAL = 100;
    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        //奇数
        Thread jsThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num.get() <= TOTAL -1){
                    if(!flag){
                        System.out.println(Thread.currentThread().getName() + "print:" + num.getAndIncrement());
                        flag = true;
                    }
                }
                latch.countDown();
            }
        });
        jsThread.setName("奇数");

        Thread osThrread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(num.get() <= TOTAL ){
                    if(flag){
                        System.out.println(Thread.currentThread().getName() + "print:" + num.getAndIncrement());
                        flag = false;
                    }
                }
                latch.countDown();
            }
        });
        osThrread.setName("偶数");

        osThrread.start();
        jsThread.start();

        latch.await();
        long end = System.currentTimeMillis();

        System.out.println("一共耗时：" + (end - start) + "秒");

    }

}
