package com.github.maxwell.base.main;

import java.util.concurrent.atomic.AtomicInteger;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/
public class TTTest extends Thread {

    static boolean foo(char c) {
        System.out.printf("%s ", c);
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (foo('A'); foo('B') && (i < 2); foo('C')) {
            i++;
            foo('D');
        }
    }

}
