package com.hikari.kiwi.modules.kiwi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.dto.BaseRequest;

@Data
@Schema(description = "对话设定查看")
public class KiwiDialogSettingFindReq extends BaseRequest {

    @Schema(description = "agentCode")
    private String agentCode;
}
