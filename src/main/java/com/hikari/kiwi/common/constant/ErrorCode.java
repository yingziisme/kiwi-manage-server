package com.hikari.kiwi.common.constant;

import lombok.Getter;

public enum ErrorCode {


    SUCCESS(200, "成功"),
    PARAM_ERROR(100000, "参数异常"),
    SYSTEM_ERROR(100001, "系统异常，请稍后重试或联系服务人员"),;

    @Getter
    private Integer code;

    @Getter
    private String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
