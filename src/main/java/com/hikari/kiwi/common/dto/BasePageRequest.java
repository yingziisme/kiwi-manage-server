package com.hikari.kiwi.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public abstract class BasePageRequest extends BaseRequest {

    @Schema(description = "排序字段")
    private String order;
    @Schema(description = "分页大小")
    private Integer limit;
    @Schema(description = "分页页码")
    private Integer page;
}
