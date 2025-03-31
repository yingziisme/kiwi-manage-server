package com.hikari.kiwi.modules.sys.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.dto.BasePageRequest;
import com.hikari.kiwi.common.dto.BaseRequest;

@Data
@Schema(description = "用户评论层级查询请求类")
public class UserCommentLevelRequest extends BasePageRequest {
    @Schema(description = "父评论 ID")
    private Long parentId;
    @Schema(description = "查询的层级数")
    private int level;
}    