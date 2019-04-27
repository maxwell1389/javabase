package com.github.maxwell.base.main;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Optional使用
 */
@Slf4j
public class OptionalTest {
    static OptObject optObject = null;

    private static String getUname() {
        Optional<OptObject> objOpt = Optional.ofNullable(optObject);
        return objOpt.map(p -> p.getOname())
                .map(oname -> oname.toUpperCase())
                .orElse(null);
    }

    public static void main(String[] args) {
        optObject = new OptObject("A", "B");
        log.info("uname {}",getUname());
    }
}
