package com.hikari.kiwi.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class BaseRequest {

    @Schema(description = "用户id", hidden = true)
    //@JsonIgnore
    private Long tokenUserId = 1L;

    @Schema(description = "请求id", hidden = true)
    //@JsonIgnore
    private String requestId = "1";
}
