package com.binarfud.backend.util;

public enum ResponseStatus {

    SUCCESS("success"),
    FAIL("fail"),
    ERROR("error");

    private final String value;

    ResponseStatus(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
