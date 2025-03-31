package com.hikari.kiwi.modules.kiwi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.dto.BasePageRequest;
import com.hikari.kiwi.common.dto.BaseRequest;

@Data
@Schema(description = "智能体信息")
public class KiwiAiAgentMessageReq extends BaseRequest {

    @Schema(description = "agentId")
    private String agentId;
}
