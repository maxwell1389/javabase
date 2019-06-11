package com.github.maxwell.base.main;

import lombok.extern.slf4j.Slf4j;

/**
 * 权重测试
 */
@Slf4j
public class BoostMain {

    private double score(Integer ticket, Integer fire) {
        double factor = 0.9;
        return ticket + fire * 0.1
                + (1 - factor) * 1000000
                + Math.random() * factor * 1000000;
    }

    public static void main(String[] args) {
        BoostMain boostMain = new BoostMain();
        log.info("score:{}", boostMain.score(200000, 100));
        String str = "_500";
        log.info("str:{}", str.contains("500"));
    }
}
