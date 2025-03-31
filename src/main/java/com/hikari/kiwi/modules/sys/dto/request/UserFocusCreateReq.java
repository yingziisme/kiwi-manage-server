package com.hikari.kiwi.modules.sys.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import com.hikari.kiwi.common.dto.BaseRequest;

@Data
@ToString(callSuper = true)
public class UserFocusCreateReq extends BaseRequest {

    @Schema(description = "所属用户 ID")
    private Long userId;
    @Schema(description = "智能体编码")
    private String agentCode;
    @Schema(description = "智能体名称")
    private String agentName;
    @Schema(description = "智能体头像")
    private String asrModelId;
}
