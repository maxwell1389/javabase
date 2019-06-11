package com.github.maxwell.base.main;

import lombok.extern.slf4j.Slf4j;

/**
 * enum使用
 */
@Slf4j
public class EnumTest {
    public static void main(String[] args) {
//        log.info("enum val={} name={}", RedirectType.直播间.goType(), RedirectType.直播间.goTypeName());
//        log.info("ra {}", Math.random()*0.5*10);
        String abc = "用户Id,火牛号";
        log.info("startwith:{}", abc.equals("用户Id,火牛号"));
    }
}
