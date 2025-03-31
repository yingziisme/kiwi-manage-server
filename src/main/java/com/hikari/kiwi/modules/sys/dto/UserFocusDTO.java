package com.hikari.kiwi.modules.sys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "用户关注数据传输对象")
public class UserFocusDTO {
    @Schema(description = "唯一标识")
    private String id;
    @Schema(description = "所属用户 ID")
    private Long userId;
    @Schema(description = "智能体编码")
    private String agentCode;
    @Schema(description = "智能体名称")
    private String agentName;
    @Schema(description = "智能体头像")
    private String asrModelId;
    @Schema(description = "创建者 ID")
    private Long creator;
    @Schema(description = "创建时间")
    private Date createdAt;
    @Schema(description = "更新者 ID")
    private Long updater;
    @Schema(description = "更新时间")
    private Date updatedAt;
}    