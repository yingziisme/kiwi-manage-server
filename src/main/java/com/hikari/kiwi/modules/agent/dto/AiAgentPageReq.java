package com.hikari.kiwi.modules.agent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.dto.BasePageRequest;
import com.hikari.kiwi.common.dto.BaseRequest;

import java.util.Date;

@Data
@Schema(description = "智能体信息")
public class AiAgentPageReq extends BasePageRequest {

}
