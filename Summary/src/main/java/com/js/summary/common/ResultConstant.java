package com.js.summary.common;

import lombok.Getter;

@Getter
public enum ResultConstant {

    SUCCESS(200, "请求成功"),
    Fail(400, "请求失败");

    private final Integer status;
    private final String message;

    ResultConstant(Integer status, String message){
        this.status = status;
        this.message = message;
    }
}
