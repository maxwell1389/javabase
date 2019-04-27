package com.github.maxwell.base.main;

public class OptObject {
    private String oname;
    private String oval;

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOval() {
        return oval;
    }

    public void setOval(String oval) {
        this.oval = oval;
    }

    public OptObject(String oname, String oval) {
        this.oname = oname;
        this.oval = oval;
    }

    @Override
    public String toString() {
        return "OptObject{" +
                "oname='" + oname + '\'' +
                ", oval='" + oval + '\'' +
                '}';
    }
}
