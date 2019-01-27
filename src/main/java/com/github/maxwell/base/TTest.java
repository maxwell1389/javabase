package com.github.maxwell.base;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/
@Slf4j
public class TTest extends Thread {

    //    int count = 0;
    private static AtomicInteger count = new AtomicInteger();

    public TTest(String name) {
        this.setName(name);
//        this.printName();
    }

    public void run() {
//            count++;
//            count.getAndIncrement();
        this.printName();
    }

    private void printName() {
        String name = Thread.currentThread().getName();
        System.out.printf("%s: %d, ", name, count.getAndIncrement());
    }

    private static void test() {
        int i = 0;
        i++;
        log.info("i:{}", i);
    }


    public static void main(String[] args) throws InterruptedException {
//        String name = Thread.currentThread().getName();
//        System.out.printf("%s main:" , name);
/*        for (int i = 0; i < 100; i++){
            TTest tTest = new TTest("Test" + i);
            tTest.start();
        }*/
/*        String str = "1234";
        int ii = Integer.parseInt(str);
        System.out.println(ii);*/
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            }).start();
        }
    }

}
