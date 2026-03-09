package com.bite.spingbookdemo.enums;

public enum ResultCodeEnum {
    UNLOGIN(-1),
    SUCCESS(200),
    FAIL(-2);
    private int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }
}
