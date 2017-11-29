package com.iyb.ak.constants;

public enum QuotaType {
    cloudstore("cloudstore", "云存储空间"),
    attewifi("attewifi", "考勤WIFI"),
    attegps("attegps", "考勤GPS"),
    sms("sms", "短信发送");

    private String code;
    private String name;

    private QuotaType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
