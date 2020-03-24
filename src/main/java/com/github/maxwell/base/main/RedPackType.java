package com.github.maxwell.base.main;

public enum RedPackType {

    FC("f", "火晶回馈"),
    redpack("914871056", "红包广告"),
    AD("a", "广告");

    private final String type;
    private final String desc;

    private RedPackType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }
}
