package com.hikari.kiwi.modules.agent.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hikari.kiwi.common.entity.BaseEntity;

import java.util.Date;

@Data
@TableName("ai_agent_template")
public class AiAgentTemplate extends BaseEntity {


    @Schema(description = "智能体编码")
    @TableField("agent_code")
    private String agentCode;

    @Schema(description = "智能体名称")
    @TableField("agent_name")
    private String agentName;

    @Schema(description = "语音识别模型标识")
    @TableField("asr_model_id")
    private String asrModelId;

    @Schema(description = "语音活动检测标识")
    @TableField("vad_model_id")
    private String vadModelId;

    @Schema(description = "大语言模型标识")
    @TableField("llm_model_id")
    private String llmModelId;

    @Schema(description = "语音合成模型标识")
    @TableField("tts_model_id")
    private String ttsModelId;

    @Schema(description = "音色标识")
    @TableField("tts_voice_id")
    private String ttsVoiceId;

    @Schema(description = "记忆模型标识")
    @TableField("mem_model_id")
    private String memModelId;

    @Schema(description = "意图模型标识")
    @TableField("intent_model_id")
    private String intentModelId;

    @Schema(description = "角色设定参数")
    @TableField("system_prompt")
    private String systemPrompt;

    @Schema(description = "语言编码")
    @TableField("lang_code")
    private String langCode;

    @Schema(description = "交互语种")
    @TableField("language")
    private String language;

    @Schema(description = "排序权重")
    @TableField("sort")
    private Integer sort;
}
