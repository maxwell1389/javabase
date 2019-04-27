package com.github.maxwell.base.thread.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * T2需执行的任务
 * 洗茶壶、洗茶杯、拿茶叶
 */
@Slf4j
public class T2Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        log.info("T2: 洗茶壶。。。");
        TimeUnit.SECONDS.sleep(1);
        log.info("T2：洗茶杯。。。");
        TimeUnit.SECONDS.sleep(1);
        log.info("T2：拿茶叶。。。");
        TimeUnit.SECONDS.sleep(1);
        return "龙井";
    }
}
