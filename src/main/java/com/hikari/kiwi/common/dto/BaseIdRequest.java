package com.hikari.kiwi.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BaseIdRequest extends BaseRequest{

    @Schema(description = "数据id")
    private String id;
}
