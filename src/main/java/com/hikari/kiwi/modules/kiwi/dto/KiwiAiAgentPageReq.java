package com.hikari.kiwi.modules.kiwi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.dto.BasePageRequest;

@Data
@Schema(description = "智能体信息")
public class KiwiAiAgentPageReq extends BasePageRequest {

}
