package com.github.maxwell.base.main;

public enum WuFuType {

    CS(888, "长寿"),
    FG(1888, "富贵"),
    KN(3888, "康宁"),
    HD(5888, "好德"),
    SZ(8888, "善终");


    private final Integer num;
    private final String desc;

    private WuFuType(Integer num, String desc) {
        this.num = num;
        this.desc = desc;
    }

    public Integer getNum() {
        return this.num;
    }

    public String getDesc() {
        return this.desc;
    }
}
