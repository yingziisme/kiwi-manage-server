package com.hikari.kiwi.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.hikari.kiwi.common.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_focus_tb")
public class UserFocus extends BaseEntity {


    @Schema(description = "所属用户 ID")
    private Long userId;
    @Schema(description = "智能体编码")
    private String agentCode;
    @Schema(description = "智能体名称")
    private String agentName;
    @Schema(description = "智能体头像")
    private String asrModelId;
}
