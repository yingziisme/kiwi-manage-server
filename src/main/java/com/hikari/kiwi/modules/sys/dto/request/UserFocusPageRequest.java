package com.hikari.kiwi.modules.sys.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.dto.BasePageRequest;

@Data
@Schema(description = "用户关注分页请求类")
public class UserFocusPageRequest extends BasePageRequest {
    // 可根据需要添加额外的查询条件
}    