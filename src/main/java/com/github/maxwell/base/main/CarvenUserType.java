package com.github.maxwell.base.main;


public enum CarvenUserType {
    USER("user", "用户"),
    ANCHOR("anchor", "主播");

    public String utype;
    public String msg;

    CarvenUserType(String utype, String msg) {
        this.utype = utype;
        this.msg = msg;
    }


}
