package com.github.maxwell.base.push;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Message {

    private final String pushId = Util.genMsgId();

    protected Object type;

    String title;

    String desc;

    private String picUrl;

    private String target;

    String ctag;

    Long ts = new Date().getTime();

    private transient long expireMillis = 24 * 3600 * 1000;

    private transient Map<String, JsonElement> data = new HashMap<>();

    public Message() {
    }

    public Message(int type) {
        this.type = type;
    }

    public Message(String type) {
        this.type = type;
    }

    public Message(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    /**
     * @param type 消息类型，http://tool.waqu.com/wiki/pages/viewpage.action?pageId=2328063
     * @return
     */
    public static Message of(int type) {
        return new Message(type);
    }

    public static Message of(String type) {
        return new Message(type);
    }

    /**
     * @param type       消息类型，http://tool.waqu.com/wiki/pages/viewpage.action?pageId=2328063
     * @param customData 根据实际场景提供数据，可以是字符串或者任意结构化的数据
     * @return
     */
    public static Message of(int type, Object customData) {
        return new Message(type).setCustomData(customData);
    }

    public static Message of(String type, Object customData) {
        return new Message(type).setCustomData(customData);
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public Message setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Message setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String toString() {
        return title + "-" + desc;
    }

    public String getCtag() {
        return ctag;
    }

    public Message setCtag(String ctag) {
        this.ctag = ctag;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getPushId() {
        return pushId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public Message setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    public long getExpireMillis() {
        return expireMillis;
    }

    public Message setExpireMillis(long expireMillis) {
        this.expireMillis = expireMillis;
        return this;
    }

    public Message setData(Object data) {
        this.addCustomData("data", data);
        return this;
    }

    public Message setCustomData(Object data) {
        this.addCustomData("data", data);
        return this;
    }

    public Message addCustomData(String key, Object value) {
        this.data.put(key, GsonTools.gson.toJsonTree(value));
        return this;
    }

    public String toJson() {
        String json = GsonTools.gson.toJson(this);
        if (data.isEmpty()) {
            return json;
        }
        JsonObject jo = GsonTools.gson.fromJson(json, JsonObject.class);
        for (Map.Entry<String, JsonElement> entry : this.data.entrySet()) {
            jo.add(entry.getKey(), entry.getValue());
        }
        return GsonTools.gson.toJson(jo);
    }

}
