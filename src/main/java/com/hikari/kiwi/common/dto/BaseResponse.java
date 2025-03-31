package com.hikari.kiwi.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.constant.ErrorCode;

@Data
public class BaseResponse<T> {

    @Schema(description = "响应消息")
    private String msg;
    @Schema(description = "响应码")
    private Integer code;
    @Schema(description = "请求id")
    private String requestId;

    @Schema(description = "数据")
    private T data;


    BaseResponse(String msg, Integer code, String requestId, T data) {
        this.msg = msg;
        this.code = code;
        this.requestId = requestId;
        this.data = data;
    }

    BaseResponse(ErrorCode code, String requestId, T data) {
        this.msg = code.getMsg();
        this.code = code.getCode();
        this.requestId = requestId;
        this.data = data;
    }

    public static <K> BaseResponse<K> buildSuccessResponse(String requestId) {
        return new BaseResponse<>(ErrorCode.SUCCESS, requestId, null);
    }

    public static <K> BaseResponse<K> buildSuccessResponse(String requestId, K data) {
        return new BaseResponse<K>(ErrorCode.SUCCESS, requestId, data);
    }

    public static <K> BaseResponse<K> buildFailureResponse(ErrorCode code, String requestId) {
        return new BaseResponse<>(code, requestId, null);
    }

    public static <K> BaseResponse<K> buildFailureResponse(ErrorCode code, String requestId, K data) {
        return new BaseResponse<>(code, requestId, data);
    }

    public static <K> BaseResponse<K> buildFailureResponse(String msg, Integer code, String requestId) {

        return new BaseResponse<>(msg, code, requestId, null);
    }

    public static <K> BaseResponse<K> buildFailureResponse(String msg, Integer code, String requestId, K data) {

        return new BaseResponse<>(msg, code, requestId, data);
    }
}
