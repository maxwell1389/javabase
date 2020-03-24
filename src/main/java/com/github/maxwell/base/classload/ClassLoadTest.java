package com.github.maxwell.base.classload;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassLoadTest {
    public static void main(String[] args) {
        ClassTest classTest = ClassTest.getInstance();
        log.info("count1={}", ClassTest.count1);
        log.info("count1={}", ClassTest.count2);
    }
}
