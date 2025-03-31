package com.hikari.kiwi.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class BaseRequest {

    @Schema(description = "用户id")
    private Long tokenUserId = 1L;

    @Schema(description = "请求id")
    private String requestId = "1";
}
