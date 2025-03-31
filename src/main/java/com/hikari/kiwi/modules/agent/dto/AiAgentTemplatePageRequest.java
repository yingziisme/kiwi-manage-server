package com.hikari.kiwi.modules.agent.dto;

import lombok.Data;
import lombok.ToString;
import com.hikari.kiwi.common.dto.BasePageRequest;

@Data
@ToString(callSuper = true)
public class AiAgentTemplatePageRequest  extends BasePageRequest {
    // 可以添加额外的查询条件
}
