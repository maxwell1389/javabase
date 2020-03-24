package com.github.maxwell.base.push;

import com.gexin.rp.sdk.base.IQueryResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetuiPusher {

    private final static Logger logger = LoggerFactory.getLogger(GetuiPusher.class);

    private String url;

    private String defaultTitle;

    private String appId;

    private String appKey;

    private String masterSceret;

    private IGtPush pusher;

    public GetuiPusher(String serviceUrl, String appId, String appKey, String appMasterSceret) {
        this.url = serviceUrl;
        this.appId = appId;
        this.appKey = appKey;
        this.masterSceret = appMasterSceret;

        this.pusher = new IGtPush(url, appKey, masterSceret);
    }

    private final static int GETUI_LIST_PUSH_LIMIT = 1000;

    public PushResult pushById(List<String> clientIds, JsonObject msg, long expireMillis) {
        if (CollectionUtils.isEmpty(clientIds))
            return PushResult.fail("empty clients");
        logger.info("push to {} clients: {}", clientIds.size(), msg.toString());
        if (clientIds.size() > GETUI_LIST_PUSH_LIMIT) {
            logger.warn("too many clients {}, limit 1000", clientIds.size());
        }
        ListMessage getuiMsg = this.setupMessage(new ListMessage(), this.setupTemplate(msg), expireMillis);
        for (int i = 0; i < clientIds.size(); i += GETUI_LIST_PUSH_LIMIT) {
            List<Target> targets = clientIds.subList(i, Math.min(clientIds.size(), i + GETUI_LIST_PUSH_LIMIT)).stream()
                    .map(cid -> {
                        Target target = new Target();
                        target.setAppId(appId);
                        target.setClientId(cid);
                        return target;
                    }).collect(Collectors.toList());

            String taskId = pusher.getContentId(getuiMsg);
            Map<String, Object> result = pusher.pushMessageToList(taskId, targets).getResponse();
            logger.info("push to {} clients, resp: {}", targets.size(), result.toString());
        }
        return PushResult.ok();
    }

    public <T extends com.gexin.rp.sdk.base.impl.Message> T setupMessage(T msg, AbstractTemplate data,
                                                                         long msgExpireMilli) {
        msg.setData(data);
        // 推送的网络类型，0不限制，1只在wifi下推送
        msg.setPushNetWorkType(0);
        // 是否离线存储消息
        msg.setOffline(true);
        // 消息离线存储过期时间
        msg.setOfflineExpireTime(msgExpireMilli);
        return msg;
    }

    private TransmissionTemplate setupTemplate(JsonObject body) {
        if (!body.has("title")) {
            body.addProperty("title", this.defaultTitle);
        }
        String title = body.get("title").getAsString();
        String desc = body.has("desc") ? body.get("desc").getAsString() : "";
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(this.appId);
        template.setAppkey(this.appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        APNPayload payload = new APNPayload();
        payload.setAutoBadge("1");
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setTitle(title);
        alertMsg.setBody(desc);
        payload.setAlertMsg(alertMsg);
//		for (Map.Entry<String, JsonElement> entry : body.entrySet()) {
//			payload.addCustomMsg(entry.getKey(), entry.getValue().toString());
//		}

        Map<String, Object> mapData = GsonTools.gson.fromJson(body.toString(),
                new TypeToken<Map<String, Object>>() {
                }.getType());
        for (Object key : mapData.keySet()) {
            if (mapData.get(key) != null) {
                payload.addCustomMsg(key.toString(), mapData.get(key));
            }
        }

        template.setAPNInfo(payload);

        String content = this.checkContent(body);
        template.setTransmissionContent(content);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    protected String checkContent(JsonObject body) {
        if (body.has("type")) {
            Integer type = null;
            try {
                type = body.get("type").getAsInt();
            } catch (Exception e) {
                // pass, type might not be integer
            }
            if (type != null) {

                // swap title & desc
                String oTitle = body.has("title") ? body.get("title").getAsString() : "蛙趣视频";
                String oDesc = body.has("desc") ? body.get("desc").getAsString() : "";
                body.addProperty("title", oDesc);
                body.addProperty("desc", oTitle);
                String content = body.toString();
                // reset title & desc
                body.addProperty("title", oTitle);
                body.addProperty("desc", oDesc);
                return content;
            }
        }

        return body.toString();
    }

    public ClientStatus getClientStatus(String clientId) {
        IQueryResult result = pusher.getClientIdStatus(appId, clientId);
        String res = ((String) result.getResponse().get("result")).toLowerCase();
        ClientStatus status = new ClientStatus(clientId);
        if ("online".equals(res)) {
            status.setStatus(ClientStatus.Status.ONLINE);
            status.setLastLoginTime(System.currentTimeMillis());
        } else if (result.getResponse().containsKey("lastLogin")) {
            status.setStatus(ClientStatus.Status.OFFLINE);
            status.setLastLoginTime((long) result.getResponse().get("lastLogin"));
        } else if ("appiderror".equals(res)) {
            status.setStatus(ClientStatus.Status.AppIdErr);
        } else if ("tokenmd5nousers".equals(res)) {
            status.setStatus(ClientStatus.Status.NoUser);
        } else {
            logger.error("UNKNOW res: {}", res);
            status.setStatus(ClientStatus.Status.UNKNOW_ERR);
        }
        return status;
    }


    public final static void main(String... args) {
//		GetuiPusher pusher = new GetuiPusher("http://sdk.open.api.igexin.com/apiex.htm", "SlH6YzZnZrA9S91RqLWx08",
//				"krkzrPsMRh8nMgbI5DfFU6", "fylqHE73WAAOa9AxOkJUnA");

        GetuiPusher pusher = new GetuiPusher("http://sdk.open.api.gepush.com/apiex.htm", "SlH6YzZnZrA9S91RqLWx08",
                "krkzrPsMRh8nMgbI5DfFU6", "fylqHE73WAAOa9AxOkJUnA");
		
/*
		UpdateInfo info = new UpdateInfo();
		info.setTitle("蛙趣视频紧急更新升级");
		info.setContent("");
		info.setPopTitle("蛙趣视频");
		info.setPopContent("蛙趣新版本恢复看视频功能和直播功能！不好意思，这两天蛙趣旧版出了点小问题，下载新版恢复正常使用，感谢您长期以来的信任。");
		info.setPopImage("");
		info.setDownloadUrl("http://static.feixun.tv/m/dl/and/general_video_baidu.apk");
		ListMessage msg = pusher.buildPopLoadMessage(info);
		pusher.pushById(Arrays.asList("d3856562a7f15d018f8921b5475c4ddf","520e67f41521ff30f1b6ae3472035f08","6453ed83249025ad22773f5d0b65f0ea","a997ae29c441e988a6752cf514743ad7"), msg);
*/

        ClientStatus clientStatus = pusher.getClientStatus("bc89a410796fc877c1c25581327186a7");
        logger.info("status {}", GsonTools.gson.toJson(clientStatus));
//		Message msg = new Message(PushType.TEXT, "test-title-lxb", "test-desc-lxb");
        Message msg = Message.of("vote").setTitle("火牛视频").setDesc("一叶之秋成为我第6个打赏者，马上去看看");
        JsonObject body = GsonTools.gson.fromJson(GsonTools.gson.toJson(msg), JsonObject.class);
//		pusher.pushById(Arrays.asList("d3856562a7f15d018f8921b5475c4ddf","520e67f41521ff30f1b6ae3472035f08","6453ed83249025ad22773f5d0b65f0ea","a997ae29c441e988a6752cf514743ad7"), body, 3600 * 1000);
        pusher.pushById(Arrays.asList("bc89a410796fc877c1c25581327186a7", "a842c8c279ae6dd1da4f855007a54f93"), body, 2 * 3600 * 1000);

    }

}
