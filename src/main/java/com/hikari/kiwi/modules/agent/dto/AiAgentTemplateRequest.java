package com.hikari.kiwi.modules.agent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.dto.BaseRequest;

@Data
public class AiAgentTemplateRequest extends BaseRequest {

    @Schema(description = "智能体唯一标识")
    private Long id;

    @Schema(description = "智能体编码")
    private String agentCode;

    @Schema(description = "智能体名称")
    private String agentName;

    @Schema(description = "语音识别模型标识")
    private String asrModelId;

    @Schema(description = "语音活动检测标识")
    private String vadModelId;

    @Schema(description = "大语言模型标识")
    private String llmModelId;

    @Schema(description = "语音合成模型标识")
    private String ttsModelId;

    @Schema(description = "音色标识")
    private String ttsVoiceId;

    @Schema(description = "记忆模型标识")
    private String memModelId;

    @Schema(description = "意图模型标识")
    private String intentModelId;

    @Schema(description = "角色设定参数")
    private String systemPrompt;

    @Schema(description = "语言编码")
    private String langCode;

    @Schema(description = "交互语种")
    private String language;

    @Schema(description = "排序权重")
    private Integer sort;

    @Schema(description = "创建者 ID")
    private Long creator;

    @Schema(description = "创建时间")
    private String createdAt;

    @Schema(description = "更新者 ID")
    private Long updater;

    @Schema(description = "更新时间")
    private String updatedAt;
}
