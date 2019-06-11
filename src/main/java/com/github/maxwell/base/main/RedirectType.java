package com.github.maxwell.base.main;

/**
 * push msg 跳转类型
 */
public enum RedirectType {

    文字图片(""),
    直播间("live"),
    视频("video"),
    链接("web");

    private String optype;

    RedirectType(String optype) {
        this.optype = optype;
    }

    public String goType() {
        return this.optype;
    }

    public String goTypeName() {
        return this.name();
    }
}
