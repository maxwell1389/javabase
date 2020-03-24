package com.github.maxwell.base.main;

import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.codec.digest.DigestUtils;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

@Slf4j
public class HttpRequestMain {
    private static final String videoUrl="http://114.55.42.186:9898/api/v1/ad/xiyou/advertNotify.json";
    public static String advertNotify(){
        String adid="1235";
        String appid="3373";
        String ordernum="1234889";
        String dlevel="1";
        String deviceid="1";
        /**
         * and:5b1f410ea314985a3aa3147f 5c9b4736a314986a4b9daafa
         *
         * ios:5c3c04b9a314983f706fc16f
         *
         * test:5b305f76a314985f2783ba97 100118, 5d2fc136a3149801f40b3e95 100312, 5b613f40a3149826db366205 137686
         * 5c984ceba314981017c5a3a0 100296
         */
        String appsign="5c984ceba314981017c5a3a0";
        String price="0.1";
        String money="1.999999";
        String merid="13227574";
        String altStr="OW9nNzhwN2U4";
        String preStr="MXZiOGNwYnBj";
        String adname="青云诀1314期";
        String pagename="com.aotian";
        StringBuffer bs=new StringBuffer("");
        bs.append(altStr);
        bs.append(preStr);
        byte[] source=bs.toString().getBytes();
        String plain=adid+appid+ordernum+dlevel+deviceid+appsign+price+money+new String(Base64.getDecoder().decode(source));

        String keycode= DigestUtils.md5Hex(plain).toUpperCase();
        System.out.println("plain:"+plain);
        System.out.println("cipher:"+keycode);

        String sr=HttpRequest.get(videoUrl+
                "?adid="+adid
                        +"&appid="+appid
                        +"&ordernum="+ordernum
                        +"&dlevel="+dlevel
                        +"&deviceid="+deviceid
                        +"&appsign="+appsign
                        +"&price="+price
                        +"&money="+money
                        +"&adname="+adname
                        +"&pagename="+pagename
                        +"&keycode="+keycode).body();
//        System.out.println("结果："+sr);
        return sr;

    }

    public static void main(String[] args) {
//        String response = HttpRequest.get("http://www.baidu.com").body();
        String resp = advertNotify();
        log.info("resp {}", resp);
    }
}
