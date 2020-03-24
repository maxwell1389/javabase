package com.github.maxwell.base.push;

import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpRequestMain {
    private static final String videoUrl = "http://100.98.27.223/push/getClientStatus.json?appName=firebull&cid=bc89a410796fc877c1c25581327186a7";

    public static String advertNotify() {

        String sr = HttpRequest.get(videoUrl).body();
//        System.out.println("结果："+sr);
        return sr;

    }

    public static void main(String[] args) {
//        String response = HttpRequest.get("http://www.baidu.com").body();
        String resp = advertNotify();
        log.info("resp {}", resp);
    }
}
