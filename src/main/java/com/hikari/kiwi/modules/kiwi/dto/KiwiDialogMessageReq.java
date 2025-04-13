package com.hikari.kiwi.modules.kiwi.dto;

import com.hikari.kiwi.common.dto.BaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@Schema(description = "智能体信息")
public class KiwiDialogMessageReq extends BaseRequest {

    @Schema(description = "agentCode")
    private String agentCode;
    @Schema(description = "message")
    private String message;
}
