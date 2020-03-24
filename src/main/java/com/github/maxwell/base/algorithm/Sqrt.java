package com.github.maxwell.base.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * 已知 sqrt (2)约等于 1.414，要求不用数学库，求 sqrt (2)精确到小数点后 10 位
 * <p>
 * 1. 已知 sqrt(2)约等于 1.414，那么就可以在(1.4, 1.5)区间做二分
 * 查找，如：
 * a) high=>1.5
 * b) low=>1.4
 * c) mid => (high+low)/2=1.45
 * d) 1.45*1.45>2 ? high=>1.45 : low => 1.45
 * e) 循环到 c)
 * <p>
 * 2. 退出条件
 * a) 前后两次的差值的绝对值<=0.0000000001, 则可退出
 */
@Slf4j
public class Sqrt {
    private static final double EPSILON = 0.0000000001;

    private static double sqrt() {
        double low = 1.4, high = 1.5;
        double mid = (low + high) / 2;
        while (high - low > EPSILON) {
            if ((mid * mid) > 2) high = mid;
            else low = mid;
            mid = (low + high) / 2;
        }
        return mid;
    }

    public static void main(String[] args) {
        log.info("v {}", sqrt());
    }
}
